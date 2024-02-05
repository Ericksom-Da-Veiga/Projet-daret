package com.example.daret.dto.user;

import java.time.LocalDate;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;

public record User_DTO(
		
		@NotBlank
		String nom,
		@NotBlank
		String prenom,
		@Email
		@NotBlank
		String mail,
		@NotBlank
		String mot_passe,
		String roles,
		@FutureOrPresent
		LocalDate date_inscription
		) 
{}