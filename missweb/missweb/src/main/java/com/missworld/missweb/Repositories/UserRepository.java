package com.missworld.missweb.Repositories;

import com.missworld.missweb.Models.Users;
import com.missworld.missweb.Models.Votes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
//    Users findByUser_id(long user_id);
    Users findByEmailAndPassword(String email, String password);
    Users findByUserId(long userId);
}
