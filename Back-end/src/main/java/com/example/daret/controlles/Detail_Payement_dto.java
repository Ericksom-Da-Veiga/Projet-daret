package com.example.daret.controlles;

import java.time.LocalDate;

import com.example.daret.entite.Payements;

public record Detail_Payement_dto(
    Long id,
    Long id_admin,
    Long id_daret,
    Long id_membre,
    LocalDate date_payement
) {
    public Detail_Payement_dto(Payements payements){
        this(
            payements.getId(),
            payements.getId_membre(),
            payements.getId_daret(),
            payements.getId_admin(),      
            payements.getDate_payement()
            );
    }
} 