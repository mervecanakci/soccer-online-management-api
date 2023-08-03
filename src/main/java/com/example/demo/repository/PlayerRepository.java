package com.example.demo.repository;


import com.example.demo.model.Player;
import com.example.demo.model.enums.TransferState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
    List<Player> findAllByTransferStateIsNot(TransferState transferState);

    // TransferState i hangi durumda istemiyorsan o durumu dışlayacak ve diğer durumları getirecek
    //playerManager.getAll da yapıyoruz bu filtreleme işlemini
}
