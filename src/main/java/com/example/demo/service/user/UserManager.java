package com.example.demo.service.user;


import com.example.demo.model.Team;
import com.example.demo.model.User;
import com.example.demo.repository.TeamRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.team.TeamImpl;
import com.example.demo.service.team.TeamRequest;
import com.example.demo.service.team.TeamResponse;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
@AllArgsConstructor
public class UserManager implements UserService {
    private final UserRepository repository;
    private final ModelMapper mapper;
    private final UserBusinessRules rules;
    private final TeamImpl teamManager;
    private final TeamRepository teamRepository;

    @Override
    public List<UsersResponse> getAll() {
        List<User> users = repository.findAll();
        List<UsersResponse> response = users
                .stream()
                .map(user -> mapper.map(user, UsersResponse.class))
                .toList();

        return response;
    }

    @Override
    public UsersResponse getById(int id) {
        rules.checkIfUserExists(id);
        User user = repository.findById(id).orElseThrow();
        UsersResponse response = mapper.map(user, UsersResponse.class);

        return response;
    }

    @Override
    @Transactional
    public UsersResponse add(UserRequest request) {
        rules.checkIfUserExistsByEmail(request.getEmail());
        User user = mapper.map(request, User.class);
        repository.save(user);

        UsersResponse response = mapper.map(user, UsersResponse.class);

        return response;
    }

    @Override
    public UsersResponse update(int id, UserRequest request) {
        rules.checkIfUserExists(id);
        User user = mapper.map(request, User.class);
        user.setId(id);
        repository.save(user);
        UsersResponse response = mapper.map(user, UsersResponse.class);

        return response;
    }

    @Override
    public void delete(int id) {
        rules.checkIfUserExists(id);
        repository.deleteById(id);
    }
}
