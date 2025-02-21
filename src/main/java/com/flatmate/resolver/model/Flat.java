package com.flatmate.resolver.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "flats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Flat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String flatCode; // Unique identifier for a group of users

    @OneToMany(mappedBy = "flat", cascade = CascadeType.ALL)
    private List<User> users;
}
