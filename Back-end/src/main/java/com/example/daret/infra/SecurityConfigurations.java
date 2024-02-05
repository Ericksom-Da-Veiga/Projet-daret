package com.example.daret.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    SecurityFilter securityfilter;

    @Bean
    public SecurityFilterChain securityfilterchain(HttpSecurity http) throws Exception{

       return http.csrf(csrf -> csrf.disable())
       .sessionManagement(session -> session
       .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
       .authorizeHttpRequests(authorize -> authorize
            //Login
            .requestMatchers(HttpMethod.POST,"/login").permitAll()
            //Gestion des utilisateurs
            .requestMatchers(HttpMethod.POST,"/membre/**").permitAll()
            //.requestMatchers(HttpMethod.POST,"/membre/**").hasAuthority("ROLE_ADMIN")
            .requestMatchers(HttpMethod.GET,"/membre/**").permitAll()
            //.requestMatchers(HttpMethod.GET,"/membre/**").authenticated()
            .requestMatchers(HttpMethod.PUT,"/membre/**").hasAnyAuthority("ROLE_ADMIN")
            .requestMatchers(HttpMethod.DELETE,"/membre/**").hasAnyAuthority("ROLE_ADMIN")
           
            //Gestion des darets
            //hasAuthority("ROLE_ADMIN")
            //hasAnyAuthority("ROLE_USER")

            .requestMatchers(HttpMethod.POST,"/darets/**").permitAll()
            //.requestMatchers(HttpMethod.POST,"/darets/**").hasAuthority("ROLE_ADMIN")
            .requestMatchers(HttpMethod.GET,"/darets/**").permitAll()
            //.requestMatchers(HttpMethod.GET,"/darets/**").authenticated()
            .requestMatchers(HttpMethod.PUT,"/darets/**").hasAnyAuthority("ROLE_ADMIN")
            .requestMatchers(HttpMethod.DELETE,"/darets/**").hasAnyAuthority("ROLE_ADMIN")
            //Gestions des participations-------------------------------------------------------------------
            .requestMatchers(HttpMethod.GET,"/participantion/**").permitAll()
            //.requestMatchers(HttpMethod.GET,"/participantion/**").authenticated()
            .requestMatchers(HttpMethod.PUT,"/participantion/**").hasAnyAuthority("ROLE_ADMIN")
            .requestMatchers(HttpMethod.DELETE,"/participantion/**").hasAnyAuthority("ROLE_ADMIN")
            //Gestion des payements-----------------------------------------------------------------------------
            .requestMatchers(HttpMethod.POST,"/payements/**").permitAll()
            //.requestMatchers(HttpMethod.POST,"/payements/**").hasAuthority("ROLE_ADMIN")
            .requestMatchers(HttpMethod.GET,"/payements/**").permitAll()
            //.requestMatchers(HttpMethod.GET,"/payements/**").authenticated()
            //Gestion des Versement--------------------------------------------------------
            .requestMatchers(HttpMethod.POST,"/versement/**").permitAll()
            //.requestMatchers(HttpMethod.POST,"/versement/**").hasAuthority("ROLE_ADMIN")
            .requestMatchers(HttpMethod.GET,"/versement/**").permitAll()
            //.requestMatchers(HttpMethod.GET,"/versement/**").authenticated()
            //Gestion des utilisateur--------------------------------------------------
            .requestMatchers(HttpMethod.POST,"/user/**").permitAll()
            .requestMatchers(HttpMethod.GET,"/user/**").permitAll()
            //tous les autres request 
            .anyRequest().denyAll()
        )
        .addFilterBefore(securityfilter,UsernamePasswordAuthenticationFilter.class)
        .build();
    }

    //para mostrar para o spring aonde ele deve pegar o AuthenticationManager
    @Bean
    public AuthenticationManager authenticationmanager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    //para criptar o password do usuario
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}