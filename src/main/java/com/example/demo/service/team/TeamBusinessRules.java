package com.example.demo.service.team;


import com.example.demo.common.constants.Messages;
import com.example.demo.core.exceptions.BusinessException;
import com.example.demo.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamBusinessRules {

    private final TeamRepository repository;

    public void checkIfTeamExistsById(int id) {
        if (!repository.existsById(id)) throw new BusinessException(Messages.Team.NOT_EXISTS);
    }

}