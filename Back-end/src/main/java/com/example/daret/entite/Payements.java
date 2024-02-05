package com.example.daret.entite;

import java.time.LocalDate;

import com.example.daret.dto.payements.Post_Payement_dto;

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
@Table(name="payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payements {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long id_membre;
    private Long id_daret;
    private Long id_admin;
    private LocalDate date_payement;

    public Payements(Long id_admin, @Valid Post_Payement_dto payement_dto) {
        this.id_admin = id_admin;
        this.id_daret = payement_dto.id_daret();
        this.id_membre = payement_dto.id_membre();
        this.date_payement = payement_dto.date_payement();
    }
}
