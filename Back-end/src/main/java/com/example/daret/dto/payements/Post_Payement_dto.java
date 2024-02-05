package com.example.daret.dto.payements;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

public record Post_Payement_dto(
    @NotNull
    Long id_membre,
    @NotNull
    Long id_daret,
    @FutureOrPresent
    LocalDate date_payement
) {
} 