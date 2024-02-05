package com.example.daret.dto.darets;

import java.time.LocalDate;

import com.example.daret.entite.Darets;

public record Liste_darets_dto(
    Long id,
    String nom,
    String description,
    LocalDate date_debut,
    int periodicite,
    int maximun_participant,
    boolean activer,
    Long montant
) {
    public Liste_darets_dto(Darets daret){
        this(
            daret.getId(), 
            daret.getNom(), 
            daret.getDescription(),
            daret.getDate_debut(),
            daret.getPeriodicite(),
            daret.getMaximun_participant(),
            daret.getActiver(),
            daret.getMontant()            
            );
    }
    
}