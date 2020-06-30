package com.example.demo.persistance;

import com.example.demo.domain.Mitarbeiter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MitarbeiterRepository extends JpaRepository<Mitarbeiter, String> {
}
