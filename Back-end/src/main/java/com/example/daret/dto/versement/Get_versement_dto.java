package com.example.daret.dto.versement;

import java.time.LocalDate;

import com.example.daret.entite.Versement;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

public record Get_versement_dto(
    @NotNull
    Long id_membre,
    @NotNull
    Long id_daret,
    @FutureOrPresent
    LocalDate date_versement
) {

    public Get_versement_dto(Versement versement) {
       this(
        versement.getId_membre(),
        versement.getId_daret(),
        versement.getDate_versement()
       );
    }

}
