package com.football.gestionare_players.repository;

import com.football.gestionare_players.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player,Integer> { }
