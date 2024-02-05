package com.example.daret.entite;

import java.time.LocalDate;
import com.example.daret.dto.participation.Post_participation_dto;
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
@Table(name="participation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Participation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long id_membre;
    private Long id_daret;
    private int quantite; //combien de place il vas prendre dans le dare --maximum 2
    private LocalDate date_participation;   
    
    
    public Participation(Long id_membre,Long id_darets,@Valid Post_participation_dto data) {
        this.id_daret = id_darets;
        this.id_membre = id_membre;
        this.quantite = data.quantite();
        this.date_participation = data.date_participation();
    }
}