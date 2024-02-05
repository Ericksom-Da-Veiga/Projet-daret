package com.example.daret.dto.participation;

import java.time.LocalDate;

import com.example.daret.entite.Participation;

public record Liste_participations_dto(
    Long id,
    Long id_membre,
    Long id_darets,
    int quantite,
    LocalDate date_participation
) {
    public Liste_participations_dto(Participation participation){
        this(
            participation.getId(),
            participation.getId_membre(),
            participation.getId_daret(),
            participation.getQuantite(),
            participation.getDate_participation()
            );
    }
}