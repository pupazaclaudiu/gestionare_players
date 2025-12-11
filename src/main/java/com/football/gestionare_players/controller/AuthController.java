package com.football.gestionare_players.controller;

import com.football.gestionare_players.model.Utilizator;
import com.football.gestionare_players.repository.UtilizatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UtilizatorRepository utilizatorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //Login first

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    //Register (get)

    @GetMapping("/register")
    public String showRegisterForm(Model model){
        model.addAttribute("registerDto",new RegisterDto());
        return "register";
    }

    //Register (post)
    @PostMapping("/register")
    public String processRegister(@ModelAttribute("registerDto") RegisterDto registerDto,Model model)
    {
        //Email deja folosit
        if(utilizatorRepository.findByEmail(registerDto.getEmail()).isPresent())
        {
            model.addAttribute("error","Email-ul este deja folosit.");
            return "register";
        }
        //Parole diferite
        if(!registerDto.getParola().equals(registerDto.getConfirmParola()))
        {
            model.addAttribute("error","Parolele diferite");
            return "register";
        }
        //Crearea utilizator
        Utilizator utilizator = new Utilizator();
        utilizator.setEmail(registerDto.getEmail());
        utilizator.setParola(passwordEncoder.encode(registerDto.getParola()));

        utilizatorRepository.save(utilizator);

        //Redirect
        return "redirect:/login?registered";
    }
}
