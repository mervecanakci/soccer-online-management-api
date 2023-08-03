package com.example.demo.service.player;


import com.example.demo.common.constants.Messages;
import com.example.demo.core.exceptions.BusinessException;
import com.example.demo.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerBusinessRules {
    private final PlayerRepository repository;

    public void checkIfPlayerExistsById(int id) {
        if (!repository.existsById(id))
            throw new BusinessException(Messages.Player.NOT_EXISTS);
    }


}
