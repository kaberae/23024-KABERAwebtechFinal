package com.missworld.missweb.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Votes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long vote_id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "userId", nullable = false, unique = true)
    private Users userId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "contestants_id", nullable = false)
    private Contestants contestant_id;

    @CreationTimestamp
    private LocalDateTime timestamp;
}
