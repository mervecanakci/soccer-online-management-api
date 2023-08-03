package com.example.demo.service.player;


import com.example.demo.model.enums.TransferState;

import java.util.List;

public interface PlayerService {
    List<PlayerResponse> getAll(boolean includeTransfer);

    PlayerResponse getById(int id);

    List<PlayerResponse> add(PlayerRequest request);

    // List<PlayerResponse>  add(PlayerRequest request, Integer marketValue);
    PlayerResponse update(int id, PlayerRequest request);

    void delete(int id);

    void changeTransferState(int playerId, TransferState transferState);


}
