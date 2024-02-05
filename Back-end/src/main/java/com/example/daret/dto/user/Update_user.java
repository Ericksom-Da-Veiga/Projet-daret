package com.example.daret.dto.user;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

public record Update_user(
		@NotNull
		Long id, 

		String nom, 
		
		String prenom, 
		
		String mail,
		
		@FutureOrPresent
		LocalDate date_inscription
		) 
	{

}
