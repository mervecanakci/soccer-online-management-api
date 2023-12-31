package com.example.demo.api;


import com.example.demo.service.player.PlayerRequest;
import com.example.demo.service.player.PlayerResponse;
import com.example.demo.service.player.PlayerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/players")
public class PlayerController {
    private final PlayerService service;

    @GetMapping
  // @PreAuthorize("hasRole('ADMIN')")
    public List<PlayerResponse> getAll(@RequestParam(defaultValue = "true") boolean includeTransfer) {
        // includeTransfer i default true verdik
        return service.getAll(includeTransfer);
    }

    @GetMapping("/{id}")
 //  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public PlayerResponse getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<PlayerResponse> add(@Valid @RequestBody PlayerRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public PlayerResponse update(@PathVariable int id, @RequestBody PlayerRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }

}
