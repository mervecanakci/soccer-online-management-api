package com.example.demo.api;


import com.example.demo.service.team.TeamRequest;
import com.example.demo.service.team.TeamResponse;
import com.example.demo.service.team.TeamService;
import com.example.demo.service.transfer.TransferPlayerRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/teams")

public class TeamController {
    private final TeamService service;

    @GetMapping
    public List<TeamResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public TeamResponse getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeamResponse add(@Valid @RequestBody TeamRequest request) {
        return service.add(request);
    }

    @PostMapping("/transfer/player")
    public TeamResponse transferPlayer(@Valid @RequestBody TransferPlayerRequest request) {
        return service.addTransferPlayer( request);
    }

    @PutMapping("/{id}")
    public TeamResponse update(@PathVariable int id, @RequestBody TeamRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }


}
