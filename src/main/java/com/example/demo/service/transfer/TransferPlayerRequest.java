package com.example.demo.service.transfer;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TransferPlayerRequest {
    private int teamId;
    private int playerId;
    private double price;
}
