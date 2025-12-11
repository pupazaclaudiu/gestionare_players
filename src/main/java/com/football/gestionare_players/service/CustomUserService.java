package com.football.gestionare_players.service;



import com.football.gestionare_players.model.Admin;
import com.football.gestionare_players.model.Utilizator;
import com.football.gestionare_players.repository.AdminRepository;
import com.football.gestionare_players.repository.UtilizatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserService implements UserDetailsService {

    @Autowired
    private UtilizatorRepository utilizatorRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        //Verificare daca e admin prima data
        Admin admin =adminRepository.findByEmail(email).orElse(null);
        if(admin!=null){
            GrantedAuthority authority= new SimpleGrantedAuthority("ROLE_ADMIN");

            return new User(
                    admin.getEmail(),
                    admin.getParola(),
                    List.of(authority)
            );
        }

        //Daca nu e admin, e utilizator
        Utilizator utilizator = utilizatorRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("Utilizator negasit: "+email));

        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");

        return new User(
                utilizator.getEmail(),
                utilizator.getParola(),
                List.of(authority)
        );
    }
}
