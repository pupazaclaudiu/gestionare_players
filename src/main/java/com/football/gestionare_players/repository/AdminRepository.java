package com.football.gestionare_players.repository;

import com.football.gestionare_players.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin,Integer> {

    Optional<Admin>findByEmail(String email);
}
