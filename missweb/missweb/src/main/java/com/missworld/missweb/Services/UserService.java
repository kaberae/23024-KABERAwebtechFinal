package com.missworld.missweb.Services;

import com.missworld.missweb.Models.Users;

import java.util.List;

public interface UserService {
    List<Users> findAllUsers();
    Users saveUser(Users user);

    Users findById(long userId);

    void updateUser(Users user);

    void deleteById(long userId);

    String logConfirmation(String email,String password);
}
