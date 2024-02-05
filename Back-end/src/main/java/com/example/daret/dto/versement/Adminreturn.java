package com.example.daret.dto.versement;

public record Adminreturn(
    long id,
    String nom
) {
    public Adminreturn(long id, String nom){
        this.id = id;
        this.nom = nom;
    }
} 
