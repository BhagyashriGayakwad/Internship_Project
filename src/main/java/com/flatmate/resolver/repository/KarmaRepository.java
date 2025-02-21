package com.flatmate.resolver.repository;

import com.flatmate.resolver.model.Karma;
import com.flatmate.resolver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KarmaRepository extends JpaRepository<Karma, Long> {
    Karma findByUser(User user);
}
