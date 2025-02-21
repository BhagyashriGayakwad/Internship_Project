package com.flatmate.resolver.repository;

import com.flatmate.resolver.model.Flat;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FlatRepository extends JpaRepository<Flat, Long> {
    Optional<Flat> findByFlatCode(String flatCode);
}
