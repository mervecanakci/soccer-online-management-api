package com.example.demo.service.team;


import com.example.demo.service.player.PlayerResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamResponse {
    private int id;
    private String teamName;
    private double teamValue;
    private String teamCountry;

    private List<PlayerResponse> players;
   // private Integer leagueId;
  //  private String leagueName;
   // private String userEmail;
//    private String userLastName;
//    private String userFirstName;

}