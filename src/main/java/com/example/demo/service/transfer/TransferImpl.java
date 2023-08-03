package com.example.demo.service.transfer;


import com.example.demo.model.Transfer;
import com.example.demo.model.enums.TransferState;
import com.example.demo.repository.PlayerRepository;
import com.example.demo.repository.TransferRepository;
import com.example.demo.service.payment.PaymentService;
import com.example.demo.service.player.PlayerRequest;
import com.example.demo.service.player.PlayerResponse;
import com.example.demo.service.player.PlayerService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class TransferImpl implements TransferService {
    private final PlayerService playerService;
    private final TransferRepository repository;
    private final ModelMapper mapper;
    private final PaymentService paymentService;
    private final PlayerRepository playerRepository;
    private final TransferBusinessRules rules;
    private final Random random;
////todo: denedin olmadı bu manuel setledin
//        transfer.setId(0);
//    private void configureModelMapper( ModelMapper) {
//        // playerCountry alanını TransferResponse sınıfındaki setPlayerCountry() ile eşleştir
//        mapper.typeMap(Transfer.class, TransferResponse.class)
//                .addMapping(Transfer::getPlayerId, TransferResponse::setPlayerId);
//
//        // playerMarketValue alanını TransferResponse sınıfındaki setPlayerMarketValue() ile eşleştir
//        mapper.typeMap(Transfer.class, TransferResponse.class)
//                .addMapping(Transfer::getTeamId, TransferResponse::setTeamId);
//    }



    @Override
    public List<TransferResponse> getAll() {
        List<Transfer> transfers = repository.findAll();
        List<TransferResponse> response = transfers
                .stream()
                .map(transfer -> mapper.map(transfer, TransferResponse.class))
                .toList();

        return response;
    }

    @Override
    public TransferResponse getById(int id) {
        rules.checkIfTransferExistsById(id);
        Transfer transfer = repository.findById(id).orElseThrow();
        TransferResponse response = mapper.map(transfer, TransferResponse.class);

        return response;
    }

    @Override
    public TransferResponse returnPlayerFromTransfer(int playerId) { // player transfer listesinden çıkarmak için taransfer işlemini tamamlamak gerekiyor
        rules.checkIfPlayerIsNotUnderTransfer(playerId);
        Transfer transfer = repository.findByPlayerIdAndIsCompletedIsFalse(playerId);
        transfer.setCompleted(true);
        transfer.setEndDate(LocalDateTime.now());
        repository.save(transfer);
        playerService.changeTransferState(playerId, TransferState.NOT_TRANSFERRED);
        TransferResponse response = mapper.map(transfer, TransferResponse.class);

        return response;
    }

    @Override
    public TransferResponse add(TransferRequest request) {
        rules.checkIfPlayerUnderTransfer(request.getPlayerId());
        rules.checkPlayerAvailabilityForTransfer(playerService.getById(request.getPlayerId()).getTransferState());
      //  Transfer transfer = mapper.map(request, Transfer.class);
        Transfer transfer = new Transfer();
        transfer.setPlayer(playerRepository.findById(request.getPlayerId()).get());
        transfer.setPlayerMarketValue(processTransfer(transfer));
        transfer.setPrice(request.getPrice());
        transfer.setCompleted(false);
        transfer.setDateOfTransfer(LocalDateTime.now());
        transfer.setEndDate(null);
        repository.save(transfer);
        playerService.changeTransferState(request.getPlayerId(), TransferState.TRANSFERRED);

        /////  TransferRequest.builder().playerId(0).build(); //todo nedennn    :(

////        // payment
//        CreateTransferPaymentRequest paymentRequest = new CreateTransferPaymentRequest();
//        mapper.map(request.getTeamValue(), paymentRequest);
//        paymentRequest.setTeamId(request.getTeamId());
//        paymentRequest.setPlayerId(request.getPlayerId());
//        paymentRequest.setTeamValue(teamValueAfterTransfer(transfer));
//        paymentRequest.setPlayerMarketValue(processTransfer(transfer));
//        paymentService.processTransferPayment(paymentRequest);
//

        repository.save(transfer);
        playerService.changeTransferState(request.getPlayerId(), TransferState.TRANSFERRED);

        //  playerService.changeTransferState(transfer.getPlayerId(), TransferState.TRANSFERRED);
    //    playerService.changeTransferState(request.getPlayerId(), TransferState.TRANSFERRED);
        //TransferResponse response = mapper.map(transfer, TransferResponse.class);
        TransferResponse response = new TransferResponse();
        response.setId(transfer.getId());
        response.setTeamName(transfer.getTeamName());
        response.setTeamValue(transfer.getTeamValue());
        response.setPrice(transfer.getPrice());
        response.setPlayerMarketValue(processTransfer(transfer));
        response.setDateOfTransfer(transfer.getDateOfTransfer());

//        //todo takım değişmeyi burada service ile yapmıştım olduğundan emin değilim
//        PlayerRequest playerRequest = new PlayerRequest();
//        createPlayerRequest(request, playerRequest, transfer);
//       // transfer.setTeamId(transfer.getOldTeamId()); //todo: doğru mu
//        playerService.add(playerRequest);

        return response;

    }


    @Override
    public TransferResponse update(int id, TransferRequest request) {
        rules.checkIfTransferExistsById(id);
        Transfer transfer = mapper.map(request, Transfer.class);
        transfer.setId(id);
        transfer.setPlayerMarketValue(processTransfer(transfer));
        repository.save(transfer);
        TransferResponse response = mapper.map(transfer, TransferResponse.class);

        return response;
    }

    @Override
    public void delete(int id) {
        rules.checkIfTransferExistsById(id);
        makePlayerNotTransferIfIsCompletedFalse(id);
        int playerId = repository.findById(id).get().getPlayer().getId();
        playerService.changeTransferState(playerId, TransferState.NOT_TRANSFERRED);
        repository.deleteById(id);
    }

    private void makePlayerNotTransferIfIsCompletedFalse(int id) {
     //   int playerId = repository.findById(id).get().getPlayer().getId();
        int playerId = repository.findById(id).get().getPlayer().getId();
        if (repository.existsByPlayerIdAndIsCompletedIsFalse(playerId)) {
            playerService.changeTransferState(playerId, TransferState.NOT_TRANSFERRED);
        }
    }
//delete deki ile aynı işi mi yapıyordu acaba

    private void createPlayerRequest(TransferRequest request, PlayerRequest playerRequest, Transfer transfer) {
        PlayerResponse playerResponse = playerService.getById(request.getPlayerId());
        playerRequest.setMarketValue(transfer.getPlayerMarketValue());
    // playerRequest.setTeamId(transfer.getNewTeamId());
        //güncelledim ya değeri playerdaki marketvalue buradaki playermarketvalue gibi ileride yine kullanırsın
    }

    public double processTransfer(Transfer transfer) {
        double currentMarketValue = transfer.getPlayerMarketValue();
        double increasePercentage = 10 + (100 - 10) * random.nextDouble();
        double increaseAmount = currentMarketValue * increasePercentage / 100;
        double increasedMarketValue = currentMarketValue + increaseAmount;
        transfer.setPlayerMarketValue(increasedMarketValue);

        return increasedMarketValue;
    }
//todo kullanmadım
//    private double teamValueAfterTransfer(Transfer transfer) {
//        double teamValue = transfer.getTeamValue();
//        double playerMarketValue = transfer.getPlayerMarketValue();
//        double teamValueAfterTransfer = teamValue - playerMarketValue;
//        transfer.setTeamValue(teamValueAfterTransfer);
//
//        return teamValueAfterTransfer;
//    }
}

/**
 * todo filtreleme yap
 * Her kullanıcı bir transfer listesindeki tüm oyuncuları görebilmeli ve onları ülkeye, takım adına, oyuncu adına ve bir değere göre filtreleyebilmelidir
 */