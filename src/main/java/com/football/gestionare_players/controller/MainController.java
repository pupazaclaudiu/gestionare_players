package com.football.gestionare_players.controller;

import com.football.gestionare_players.model.Player;
import com.football.gestionare_players.repository.PlayerRepository;
import com.football.gestionare_players.service.CustomUserService;
import com.football.gestionare_players.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private  PlayerRepository playerRepository;

    @Autowired
    private PlayerService playerService;


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
    public String listPlayers(@RequestParam(required = false) String echipa,
                              @RequestParam(required = false) String pozitie,
                              @RequestParam(required = false) String picior,
                              @RequestParam(required = false) Integer varsta,
                              Model model) {

        // verificăm dacă există vreun filtru completat
        boolean areFiltre =
                StringUtils.hasText(echipa) ||
                        StringUtils.hasText(pozitie) ||
                        StringUtils.hasText(picior) ||
                        varsta != null;

        List<Player> listaFiltrata;

        if (areFiltre) {
            // chemăm metoda „magică” din service
            listaFiltrata = playerService.filtreazaJucatori(echipa, pozitie, picior, varsta);
        } else {
            // fără filtre → toți jucătorii
            listaFiltrata = playerService.getAllPlayers();
        }

        model.addAttribute("players", listaFiltrata);

        model.addAttribute("echipa", echipa);
        model.addAttribute("pozitie", pozitie);
        model.addAttribute("picior", picior);
        model.addAttribute("varsta", varsta);

        // pentru formularul de add (admin)
        model.addAttribute("playerNou", new Player());

        return "players"; // players.html
    }

    // Detalii pentru un jucător (optional)
    @GetMapping("/players/{id}")
    public String playerDetails(@PathVariable Integer id, Model model) {
        var player = playerRepository.findById(id)
                .orElse(null); // aici ai putea pune o pagină de eroare dacă e null

        model.addAttribute("player", player);
        return "player-details"; // templates/player-details.html
    }
    // Pagina de statistici (Power BI embed)
    @GetMapping("/stats")
    public String statsPage() {
        // doar returnează numele template-ului stats.html
        return "stats";
    }

}

