package com.flatmate.resolver.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Karma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int karmaPoints = 0;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
