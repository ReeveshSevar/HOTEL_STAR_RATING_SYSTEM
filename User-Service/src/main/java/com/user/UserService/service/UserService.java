package com.user.UserService.service;

import com.user.UserService.model.Users;

import java.util.List;

public interface UserService {
    Users create(Users users);
    Users getUser(String id);
    List<Users> getUsers();
    void deleteUser(String id);
}
