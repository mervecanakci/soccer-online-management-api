package com.example.demo.service.user;


import com.example.demo.service.team.TeamResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UsersResponse {
    private int id;
    private String email;
    private String password;
  //  private int teamId;
   private List<TeamResponse> teams;

}