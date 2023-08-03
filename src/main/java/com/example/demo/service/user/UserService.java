package com.example.demo.service.user;


import java.util.List;

public interface UserService {

    List<UsersResponse> getAll();

    UsersResponse getById(int id);

    UsersResponse add(UserRequest request);

    UsersResponse update(int id, UserRequest request);

    void delete(int id);
}
//todo servis kısmı tamam