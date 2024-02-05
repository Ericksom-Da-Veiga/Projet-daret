package com.example.daret.dto.participation;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

public record Post_participation_dto(
    @NotNull
    int quantite,
    @FutureOrPresent
    LocalDate date_participation
) {
} 