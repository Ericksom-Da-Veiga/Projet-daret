package com.example.daret.repository;

import com.example.daret.entite.Darets;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.daret.dto.darets.Liste_darets_dto;

public interface Darets_repository extends JpaRepository<Darets, Long>{

    @Query("SELECT d FROM Darets d WHERE d.activer = true")
    List<Liste_darets_dto> findAllByActiverTrue();

    @Query("SELECT d.maximun_participant FROM Darets d WHERE d.id = :id_daret")
    int getNumberMaximun(Long id_daret);

}
