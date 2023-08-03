package com.turkcell.socceronlinemanagement.service.league;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class LeagueResponse {

    private int id;
    private String country;
    private String leagueName;

}