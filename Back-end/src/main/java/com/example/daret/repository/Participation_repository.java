package com.example.daret.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.daret.entite.Participation;


public interface Participation_repository extends JpaRepository<Participation, Long>
{

    //@Query("SELECT COUNT(p.id) FROM Participation p WHERE p.id_membre = :id_user AND p.id_daret = :id_darets")
    @Query(value = "SELECT COUNT(p.id) FROM participation p WHERE p.id_membre = :id_user AND p.id_daret = :id_darets", nativeQuery = true)
    int findByIdmembreAndIddaret(Long id_user,Long id_darets);

   // @Query("SELECT DISTINCT d FROM Participation p JOIN Darets d ON p.id_daret = d.id WHERE p.id_membre = :id_user")
    @Query(value = "SELECT d.id,d.nom,d.description,d.date_debut,d.periodicite,d.maximun_participant,d.activer,d.montant FROM darets d INNER JOIN participation p ON p.id_daret = d.id WHERE p.id_membre = :id_user", nativeQuery = true)
    List<Object[]> findByIdmembre(Long id_user);

    @Query("SELECT SUM(p.quantite) FROM Participation p Where id_daret = :id_daret")
    int getNumberParticipant(Long id_daret);
    
}