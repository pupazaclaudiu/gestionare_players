package com.football.gestionare_players.security_config;
import com.football.gestionare_players.model.Admin;
import com.football.gestionare_players.model.Player;
import com.football.gestionare_players.model.Utilizator;
import com.football.gestionare_players.repository.AdminRepository;
import com.football.gestionare_players.repository.PlayerRepository;
import com.football.gestionare_players.repository.UtilizatorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final UtilizatorRepository utilizatorRepository;
    private final AdminRepository adminRepository;
    private final PlayerRepository playerRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(UtilizatorRepository utilizatorRepository,
                      AdminRepository adminRepository,
                      PlayerRepository playerRepository,
                      PasswordEncoder passwordEncoder) {
        this.utilizatorRepository = utilizatorRepository;
        this.adminRepository = adminRepository;
        this.playerRepository = playerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        // === UTILIZATORI ===
        if (utilizatorRepository.count() == 0) {

            Utilizator user1 = new Utilizator();
            user1.setEmail("victor@gmail.com");
            user1.setParola(passwordEncoder.encode("parola123")); // parola in clar: parola123

            Utilizator user2 = new Utilizator();
            user2.setEmail("claudiu@gmail.com");
            user2.setParola(passwordEncoder.encode("parola123"));

            utilizatorRepository.save(user1);
            utilizatorRepository.save(user2);
        }

        // === ADMINI ===
        if (adminRepository.count() == 0) {

            Admin admin = new Admin();
            admin.setEmail("admin@gmail.com");
            admin.setParola(passwordEncoder.encode("admin"));

            adminRepository.save(admin);
        }

        // === PLAYERS ===
        if (playerRepository.count() == 0) {

            Player p1 = new Player();
            p1.setNume("Ronaldo");
            p1.setPrenume("Cristiano");
            p1.setEchipa("Real Madrid");
            p1.setVarsta(39);
            p1.setPozitie("Atacant");
            p1.setGoal(700);
            p1.setAsist(200);
            p1.setSalvari(0);
            p1.setPicior("drept");

            Player p2 = new Player();
            p2.setNume("Messi");
            p2.setPrenume("Lionel");
            p2.setEchipa("Inter Miami");
            p2.setVarsta(37);
            p2.setPozitie("Mijlocas ofensiv");
            p2.setGoal(700);
            p2.setAsist(300);
            p2.setSalvari(0);
            p2.setPicior("stang");

            Player p3 = new Player();
            p3.setNume("Neuer");
            p3.setPrenume("Manuel");
            p3.setEchipa("Bayern Munchen");
            p3.setVarsta(38);
            p3.setPozitie("Portar");
            p3.setGoal(0);
            p3.setAsist(3);
            p3.setSalvari(500);
            p3.setPicior("drept");

            playerRepository.save(p1);
            playerRepository.save(p2);
            playerRepository.save(p3);
        }
    }
}




