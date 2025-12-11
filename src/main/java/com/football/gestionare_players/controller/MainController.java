package com.football.gestionare_players.controller;

import com.football.gestionare_players.repository.PlayerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {

    private final PlayerRepository playerRepository;

    public MainController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    // Pagina principală – redirect către lista de jucători
    @GetMapping("/")
    public String home() {
        return "redirect:/players";
    }

    // Lista tuturor jucătorilor
    @GetMapping("/players")
    public String listPlayers(Model model) {
        model.addAttribute("players", playerRepository.findAll());
        return "players"; // templates/players.html
    }

    // Detalii pentru un jucător (optional)
    @GetMapping("/players/{id}")
    public String playerDetails(@PathVariable Integer id, Model model) {
        var player = playerRepository.findById(id)
                .orElse(null); // aici ai putea pune o pagină de eroare dacă e null

        model.addAttribute("player", player);
        return "player-details"; // templates/player-details.html
    }
}

