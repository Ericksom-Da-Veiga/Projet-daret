package com.example.daret.dto.versement;

import java.time.LocalDate;

import org.springframework.cglib.core.Local;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

public record Post_Versement_dto(
    @NotNull
    Long id_daret,
    @FutureOrPresent
    LocalDate date_versement
) {

}
