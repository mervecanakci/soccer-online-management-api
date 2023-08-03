package com.example.demo.service.team;


import com.example.demo.service.transfer.TransferPlayerRequest;
import jakarta.validation.Valid;

import java.util.List;

public interface TeamService {
    List<TeamResponse> getAll();

   TeamResponse getById(int id);

    TeamResponse add(TeamRequest request);

    TeamResponse update(int id, TeamRequest request);

    void delete(int id);

    TeamResponse addTransferPlayer(@Valid TransferPlayerRequest request);
}
