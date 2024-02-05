package com.example.daret.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.daret.dto.payements.List_payements_dto;
import com.example.daret.entite.Payements;

public interface Payements_repository extends JpaRepository<Payements, Long> {

    @Query(value = "SELECT m.nom AS nom_membre, d.nom AS nom_daret, p.date_payement, a.nom AS nom_admin FROM payments p join darets d on p.id_daret = d.id join utilisateur m on p.id_membre = m.id join utilisateur a on p.id_admin = a.id", nativeQuery = true)
    List<Object[]>  findAllPayements();
    
}
