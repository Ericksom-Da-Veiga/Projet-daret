package com.example.daret.dto.user;

import java.time.LocalDate;

import com.example.daret.entite.Utilisateur;

public record Datas_list_membres(
	Long id, 
	String nom, 
	String prenom, 
	String mail,
	String mot_passe, 
	boolean activer,
	String role, 
	LocalDate date_debut)
	{
	
	public Datas_list_membres(Utilisateur Membre) {
		this(
			Membre.getId(),
			Membre.getNom(),
			Membre.getPrenom(),
			Membre.getMail(),
			Membre.getMot_passe(),
			Membre.getActiver(),
			Membre.getRoles(),
			Membre.getDate_inscription()
			);
	}
	
}
