package com.turkcell.socceronlinemanagement.service.league;

import com.turkcell.socceronlinemanagement.service.league.LeagueRequest;
import com.turkcell.socceronlinemanagement.service.league.LeagueResponse;

import java.util.List;

public interface LeagueService {
    List<LeagueResponse> getAll();

    LeagueResponse getById(int id);

    LeagueResponse add(LeagueRequest request);

    LeagueResponse update(int id, LeagueRequest request);

    void delete(int id);
}
