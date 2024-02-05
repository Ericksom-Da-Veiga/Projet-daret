package com.example.daret.entite;

import java.time.LocalDate;
import com.example.daret.dto.darets.PostDaret_DTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "darets")
@Getter
@Setter
@AllArgsConstructor //constructor com todos os parametros
@NoArgsConstructor //Constructor sem parametros
@EqualsAndHashCode(of = "id")

public class Darets {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String nom;
    private String description;
    private LocalDate date_debut;
    private int periodicite;  //donner le periode en semaine
    private int maximun_participant;
    private boolean activer; //utliser pour faire une exclusion logique
    private Long montant;

    public Darets(PostDaret_DTO data) {
        this.nom = data.nom();
        this.description = data.description();
        this.date_debut = data.date_debut();
        this.periodicite = data.periodicite();
        this.maximun_participant = data.maximun_participant();
        this.activer = data.activer();
        this.montant = data.montant();
    }

    public void desativer(){
        this.activer = false;
    }

    public boolean getActiver(){
        return activer;
    }
}