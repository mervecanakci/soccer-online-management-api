package com.example.demo.api;


import com.example.demo.service.user.UserRequest;
import com.example.demo.service.user.UserService;
import com.example.demo.service.user.UsersResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService service;

    @GetMapping
    public List<UsersResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public UsersResponse getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    public UsersResponse add(@Valid @RequestBody UserRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UsersResponse update(@PathVariable int id, @Valid @RequestBody UserRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}


