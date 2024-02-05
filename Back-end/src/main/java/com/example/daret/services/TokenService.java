package com.example.daret.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.daret.entite.Utilisateur;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    //metodo para gerar o token para o usuario quando ele logar
    public String GeneratedToken(Utilisateur user){
        try {
             Algorithm algorithm = Algorithm.HMAC256(secret);
             String token = JWT.create()
                                .withIssuer("Daret-API")
                                .withSubject(user.getMail())
                                .withExpiresAt(genExepirationDate())
                                .sign(algorithm);

                return token;
        } catch (JWTCreationException e) {
            throw new RuntimeException("Error pendant la generation du token",e);
        }
    }


    private Instant genExepirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("+01:00"));
    }


    public String ValidateToken(String token){
        try {
             Algorithm algorithm = Algorithm.HMAC256(secret);
                return JWT.require(algorithm)
                        .withIssuer("Daret-API")
                        .build()
                        .verify(token)
                        .getSubject();
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            return "";
        }
    } 
}