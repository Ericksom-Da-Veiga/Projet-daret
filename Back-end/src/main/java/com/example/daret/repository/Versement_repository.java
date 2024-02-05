package com.example.daret.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.daret.dto.versement.Adminreturn;
import com.example.daret.dto.versement.Versement_return_dto;
import com.example.daret.entite.Versement;

import jakarta.validation.constraints.NotNull;

public interface Versement_repository extends JpaRepository<Versement, Long>{

    //retorna o utilisador que fez a inscriçao(participaçao) em primeiro e que ainda nao recebeu o seu dinheiro (so retorna 1)
    @Query(value = "SELECT u.id, u.nom, u.prenom, u.mail, u.date_inscription FROM participation p Join utilisateur u on p.id_membre = u.id WHERE p.id_daret = :id_daret AND u.id NOT IN (select id_membre from versement where id_daret = :id_daret2) ORDER BY date_inscription ASC LIMIT 1", nativeQuery = true)
    Versement_return_dto FindUserVersement(@NotNull Long id_daret, @NotNull Long id_daret2);

    //@Query(value = "SELECT v.id_daret, u1.nom AS nome_membre, v.date_versement,  d.nom AS nome_daret FROM   versement v JOIN utilisateur u1 ON v.id_membre = u1.id JOIN darets d ON v.id_daret = d.id", nativeQuery = true)



    @Query(value = "SELECT u.id,u.nom  FROM utilisateur u WHERE roles = 'ADMIN';", nativeQuery = true)
    List<Object[]> findAdmin();

    default List<Adminreturn> findAdminMapped() {
        List<Object[]> result = findAdmin();
        return result.stream()
                .map(objects -> new Adminreturn((Long) objects[0], (String) objects[1]))
                .collect(Collectors.toList());
    }
} 
