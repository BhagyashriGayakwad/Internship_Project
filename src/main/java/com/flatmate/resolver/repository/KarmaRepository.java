package com.flatmate.resolver.repository;

import com.flatmate.resolver.model.Karma;
import com.flatmate.resolver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KarmaRepository extends JpaRepository<Karma, Long> {
    Karma findByUser(User user);
   // Optional<Karma> findByUser(User user);

    List<Karma> findTop10ByOrderByKarmaPointsDesc();
}
