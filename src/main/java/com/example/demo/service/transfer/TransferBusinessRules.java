package com.example.demo.service.transfer;


import com.example.demo.common.constants.Messages;
import com.example.demo.core.exceptions.BusinessException;
import com.example.demo.model.enums.TransferState;
import com.example.demo.repository.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferBusinessRules {
    private final TransferRepository repository;

    public void checkIfTransferExistsById(int id) { // transfer var mı
        if (!repository.existsById(id)) {
            throw new BusinessException(Messages.Transfer.NOT_EXISTS);
        }
    }

    public void checkIfPlayerIsNotUnderTransfer(int playerId) {
        if (!repository.existsByPlayerIdAndIsCompletedIsFalse(playerId)) { // oyuncu transfer listesinde değilse
            throw new BusinessException(Messages.Transfer.PLAYER_NOT_EXISTS);
        }
    }

    public void checkIfPlayerUnderTransfer(int playerId) { // oyuncu transfer listesindeyse
        if (repository.existsByPlayerIdAndIsCompletedIsFalse(playerId)) {
            throw new BusinessException(Messages.Transfer.PLAYER_EXISTS);
        }
    }

    public void checkPlayerAvailabilityForTransfer(TransferState transferState) { // oyuncu transfer için uygun mu
        if (transferState.equals(TransferState.TRANSFERRED)) {
            throw new BusinessException(Messages.Transfer.PLAYER_EXISTS);
        }
    }
    //todo sanki mantıklı ve gerekli değil gibi, daha farklı senoryolarda olabilir ; State.KIRALIK gibi durumlarda
    public void checkIfBalanceIsEnough(double playerMarketValue, double teamValue) {
        if (teamValue < playerMarketValue) {
            throw new BusinessException(Messages.Payment.NOT_ENOUGHT_MONEY);
        }
    }
}
