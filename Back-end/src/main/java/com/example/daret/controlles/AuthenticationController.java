package com.example.daret.controlles;

import org.springframework.web.bind.annotation.RestController;

import com.example.daret.dto.AuthenticationDTO;
import com.example.daret.dto.user.LoginResponseDTO;
import com.example.daret.entite.Utilisateur;
import com.example.daret.services.TokenService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private TokenService tokenservice;

    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    public ResponseEntity<?> Login(@RequestBody @Valid AuthenticationDTO datas){
        var UserPassword = new UsernamePasswordAuthenticationToken(datas.mail(), datas.mot_passe());
        var autentication = this.manager.authenticate(UserPassword);

        //vai criar um token utilisant o usuario que fez login  autentication.getprincipal retormna o usario que fez login
        var token = tokenservice.GeneratedToken((Utilisateur) autentication.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
        
    
}
