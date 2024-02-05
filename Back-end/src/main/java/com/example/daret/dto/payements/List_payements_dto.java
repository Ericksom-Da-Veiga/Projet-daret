package com.example.daret.dto.payements;

import java.time.LocalDate;

public record List_payements_dto(
    String nom_membre,
    String nom_daret,
    LocalDate date_payement,
    String nom_admin
) {
    public List_payements_dto(String nom_membre, String nom_daret, LocalDate date_payement, String nom_admin) {
        this.nom_membre = nom_membre;
        this.nom_daret = nom_daret;
        this.date_payement = date_payement;
        this.nom_admin = nom_admin;
    }
    
}
