package com.example.daret.dto.darets;

import java.time.LocalDate;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PostDaret_DTO(
    @NotBlank
    String nom,
    @NotBlank
    String description,
    @FutureOrPresent
    LocalDate date_debut,
    @NotNull
    int periodicite,
    @NotNull
    int maximun_participant,
    @NotNull
    boolean activer,
    @NotNull
    Long montant
) {

}