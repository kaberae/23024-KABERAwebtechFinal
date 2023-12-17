package com.missworld.missweb.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column(name = "names", nullable = false, length = 50)
    private String names;

    @Column(name = "email", unique = true,nullable = false, length = 100)
    private String email;

    @Column(name = "password", nullable = false, length = 200)
    private String password;

    @Column(name = "role", nullable = false, columnDefinition ="ENUM('USER', 'ADMIN') DEFAULT 'USER'")
    private String role;

    @Column(name = "voted", nullable = false, columnDefinition ="ENUM('YES', 'NO') DEFAULT 'NO'")
    private String voted;
}
