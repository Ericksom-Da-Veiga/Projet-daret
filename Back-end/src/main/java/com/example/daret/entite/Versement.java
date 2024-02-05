package com.example.daret.entite;

import java.time.LocalDate;

import com.example.daret.dto.versement.Post_Versement_dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="versement")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Versement {

	public Versement(@Valid Post_Versement_dto data,long id_membre) {
        this.id_daret = data.id_daret();
        this.id_membre = id_membre;
        this.date_versement = data.date_versement();
    }
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private Long id_daret;
    private Long id_membre;
    private LocalDate date_versement;
    
}
