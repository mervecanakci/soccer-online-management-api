package com.example.demo.service.user;


import com.example.demo.common.constants.Messages;
import com.example.demo.core.exceptions.BusinessException;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor //todo
public class UserBusinessRules {
    private final UserRepository repository;
    public void checkIfUserExists(int id) {
        if (!repository.existsById(id)) {
            throw new BusinessException(Messages.User.NotExists);
        }
    }
    public void checkIfUserExistsByEmail(String email) {
        if (repository.existsByEmailIgnoreCase(email)) {
            throw new BusinessException(Messages.User.Exists);
        }
    }

}
