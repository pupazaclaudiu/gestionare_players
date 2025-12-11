package com.football.gestionare_players.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name="players")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nume;
    private String prenume;
    private String echipa;
    private Integer varsta;
    private String pozitie;

    //statistici
    @Column(name = "goal")
    private Integer goluri;

    @Column(name = "asist")
    private Integer asisturi;

    private Integer salvari;

    private String picior; // referindu se la picior principal cu care suteaza jucatorul
}
