package com.example.daret.dto.user;

import java.time.LocalDate;

import com.example.daret.entite.Utilisateur;

public record DataDetailMembres(
    Long id,
	String nom,
	String prenom,
	String mail,
	String mot_passe,
	LocalDate date_inscription,
	Boolean activer,
    String roles,
    String errorMessage
) {
    //no caso de o email existir no banco de dados 
    public static DataDetailMembres withError(String errorMessage) {
        return new DataDetailMembres(null, null, null, null, null, null, null, null, errorMessage);
    }
    
    public DataDetailMembres(Utilisateur membre){
        this(
            membre.getId(),
            membre.getNom(),
            membre.getPrenom(),
            membre.getMail(),
            membre.getMot_passe(),
            membre.getDate_inscription(),
            membre.getActiver(),
            membre.getRoles(),
            null
            );
    }

    
}