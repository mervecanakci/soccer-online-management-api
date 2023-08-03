package com.example.demo.service.team;


import com.example.demo.model.Player;
import com.example.demo.model.Team;
import com.example.demo.repository.PlayerRepository;
import com.example.demo.repository.TeamRepository;
import com.example.demo.service.player.PlayerBusinessRules;
import com.example.demo.service.player.PlayerImpl;
import com.example.demo.service.player.PlayerRequest;
import com.example.demo.service.player.PlayerResponse;
import com.example.demo.service.transfer.TransferBusinessRules;
import com.example.demo.service.transfer.TransferPlayerRequest;
import com.github.javafaker.Faker;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class TeamImpl implements TeamService {
    private final TeamRepository repository;
    private final ModelMapper mapper;
    private final TeamBusinessRules rules;
    private final PlayerImpl playerManager;
    private final PlayerRepository playerRepository;
    private final TeamBusinessRules teamBusinessRules;
    private final TransferBusinessRules transferBusinessRules;
    private final PlayerBusinessRules playerBusinessRules;
    private final TeamRepository teamRepository;
    private final Random random;

    @Override
    public List<TeamResponse> getAll() {
        List<Team> teams = repository.findAll();
        List<TeamResponse> response = teams
                .stream()
                .map(team -> mapper.map(team, TeamResponse.class))
                .toList();

        return response;
    }

    @Override
    public TeamResponse getById(int id) {
        rules.checkIfTeamExistsById(id);
        Team team = repository.findById(id).orElseThrow();
        TeamResponse response = mapper.map(team, TeamResponse.class);

        return response;
    }

    @Override
    @Transactional
    public TeamResponse add(TeamRequest request) {
        Team team = mapper.map(request, Team.class);
        team.setId(0);
        repository.save(team);
        TeamResponse response = mapper.map(team, TeamResponse.class);

        PlayerRequest playerRequest = new PlayerRequest();
        List<PlayerResponse> playersForTeam = playerManager.add(playerRequest); //playerManager.add(playerRequest) ile playerManager dan playerRequest i alıyoruz
        for (PlayerResponse playerResponse : playersForTeam) {
            team.setTeamValue(team.getTeamValue() + playerRequest.getMarketValue());
            Player player = mapper.map(playerResponse, Player.class);
            player.setTeam(team);
            playerRepository.save(player);
        }

        return response;
    }


    @Override
    public TeamResponse update(int id, TeamRequest request) {
        rules.checkIfTeamExistsById(id);
        Team team = mapper.map(request, Team.class);
        team.setId(id);
        repository.save(team);
        TeamResponse response = mapper.map(team, TeamResponse.class);

        return response;
    }

    @Override
    public void delete(int id) {
        rules.checkIfTeamExistsById(id);
        repository.deleteById(id);
    }

    @Override
    public TeamResponse addTransferPlayer(@Valid TransferPlayerRequest request) {
        playerBusinessRules.checkIfPlayerExistsById(request.getPlayerId());
        transferBusinessRules.checkIfTransferExistsById(request.getPlayerId());
        teamBusinessRules.checkIfTeamExistsById(request.getPlayerId());
        final double marketValue = playerRepository.findById(request.getPlayerId()).get().getMarketValue(); //marketValue yu playerRepository den çekiyoruz
        transferBusinessRules.checkIfBalanceIsEnough(marketValue, request.getPrice()); //takımıın yeterli bakiyesi var mı diye kontrol ediyoruz
        final Player player = playerRepository.findById(request.getPlayerId()).get(); //playerId ile playerRepository den player çekiyoruz
        final Team team = teamRepository.findById(request.getTeamId()).get(); //teamId ile teamRepository den team çekiyoruz
        player.setTeam(team); //player ın takımını setliyoruz

        double increasedMarketValue = getIncreasedMarketValue(request); //artan marketValue yu hesaplıyoruz
        player.setMarketValue(increasedMarketValue); //player ın marketValue sunu setliyoruz
        this.playerRepository.save(player); //player ı kaydediyoruz
        return mapper.map(team, TeamResponse.class); //team ı döndürüyoruz
    }

    private double getIncreasedMarketValue(TransferPlayerRequest request) {
        double increasePercentage = 10 + (100 - 10) * random.nextDouble();
        double increaseAmount = request.getPrice() * increasePercentage / 100;
        return request.getPrice() + increaseAmount;

    }

//    private List<Team> generateTeam() {
//        List<Team> teams = new ArrayList<>();
//        Team team = new Team();
//        team.setTeamName(Faker.instance().team().name());
//        team.setTeamCountry(Faker.instance().address().country());
//        teams.add(team);
//        return teams;
//    }

}








