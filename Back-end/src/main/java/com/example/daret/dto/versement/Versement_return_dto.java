package com.example.daret.dto.versement;

import java.sql.Date;

import jakarta.validation.constraints.NotBlank;

public record Versement_return_dto(
    @NotBlank
    long id,
    @NotBlank
    String nom,
    @NotBlank
    String prenom,
    @NotBlank
    String mail,
    @NotBlank
    Date date_inscription
) {
    public Versement_return_dto(long id, String nom, String prenom, String mail, Date date_inscription){
        this.id = id;
        this.nom = nom;
        this.prenom =prenom;
        this.mail = mail;
        this.date_inscription = date_inscription;
    }
} 