package com.football.gestionare_players.service;

import com.football.gestionare_players.model.Player;
import com.football.gestionare_players.repository.PlayerRepository;
import jakarta.persistence.criteria.Predicate; // Atentie la import!
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification; // Atentie la import!
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.ArrayList;
import java.util.List;


@Service
public class PlayerService {

    @Autowired
    private  PlayerRepository playerRepository;


    public  List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    // metoda „magică” pentru filtrare dinamică
    public  List<Player> filtreazaJucatori(String echipa,String pozitie,String picior, Integer varsta) {

        Specification<Player> spec = (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();

            // echipa LIKE %text%
            if (StringUtils.hasText(echipa)) {
                predicateList.add(
                        cb.like(
                                cb.lower(root.get("echipa")),
                                "%" + echipa.toLowerCase() + "%"
                        )
                );
            }

            // poziție LIKE %text%
            if (StringUtils.hasText(pozitie)) {
                predicateList.add(
                        cb.like(
                                cb.lower(root.get("pozitie")),
                                "%" + pozitie.toLowerCase() + "%"
                        )
                );
            }

            // picior LIKE %text%
            if (StringUtils.hasText(picior)) {
                predicateList.add(
                        cb.like(
                                cb.lower(root.get("picior")),
                                "%" + picior.toLowerCase() + "%"
                        )
                );
            }

            // vârsta = valoarea dată
            if (varsta != null) {
                predicateList.add(
                        cb.equal(root.get("varsta"), varsta)
                );
            }

            // le unim pe toate cu AND
            return cb.and(predicateList.toArray(new Predicate[0]));
        };

        return playerRepository.findAll(spec);
    }
}
