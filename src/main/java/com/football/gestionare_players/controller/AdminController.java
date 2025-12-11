package com.football.gestionare_players.controller;

import com.football.gestionare_players.model.Player;
import com.football.gestionare_players.repository.PlayerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/players")
public class AdminController {

    private final PlayerRepository playerRepository;

    public AdminController(PlayerRepository playerRepository){
        this.playerRepository=playerRepository;
    }

    @GetMapping("/new")
    public String showCreateForm(Model model){
        model.addAttribute("player",new Player());
        return "player-form";
    }

    @PostMapping("/save")
    public String savePlayer(@ModelAttribute("player") Player pLayer){
        playerRepository.save((pLayer));
        return "redirect:/players";
    }
}
