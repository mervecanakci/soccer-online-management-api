package com.example.demo.common.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTransferPaymentRequest {
  //  private int userId;
    private int teamId;
    private int playerId;
//    private String newTeamName;
//    private String oldTeamName;
//    private String playerFirstName;
//    private String playerLastName;
    private double teamValue;
    private double playerMarketValue;

}//todo: böyle değil düzenle payment manager da kullandıklarına göre
