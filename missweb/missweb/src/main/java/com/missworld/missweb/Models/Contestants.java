package com.missworld.missweb.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Contestants {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long contestant_id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "userId", nullable = false)
    private Users userId;

    @Column(name = "names", nullable = false, length = 50)
    private String names;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "country", nullable = false, length = 50)
    private String country;

    @Column(name = "photo_url", nullable = false, length =3000)
    private String photo_url;

    @OneToMany(mappedBy = "contestant_id", cascade = CascadeType.REMOVE)
    private List<Votes> votes = new ArrayList<>();

}