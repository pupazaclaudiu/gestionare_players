package com.football.gestionare_players.controller;


import lombok.Data;

@Data
public class RegisterDto {
    private String email;
    private String parola;
    private String confirmParola;
}
