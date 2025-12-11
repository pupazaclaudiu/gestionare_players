package com.football.gestionare_players.repository;

import com.football.gestionare_players.model.Utilizator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilizatorRepository extends JpaRepository<Utilizator ,Integer> {

    Optional<Utilizator> findByEmail(String email);
}
