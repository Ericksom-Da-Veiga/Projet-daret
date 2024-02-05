package com.example.daret.dto.darets;

import java.time.LocalDate;

import com.example.daret.entite.Darets;

public record DaretsDetail(
    Long id,
    String nom,
    String descriprion,
    LocalDate date_debut,
    int periodicite,
    int maximun_participant,
    boolean activer,
    Long montant
) {
    public DaretsDetail(Darets daret){
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
