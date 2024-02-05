package com.example.daret.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.daret.dto.user.Datas_list_membres;
import com.example.daret.entite.Utilisateur;

public interface User_repository extends JpaRepository<Utilisateur, Long>{

    List<Utilisateur> findAllByActiverTrueAndRoles(String role);


    //tamben posso criar uma methodo dessa forma
    // @Query("SELECT u FROM Utilisateur u WHERE u.activer = true AND u.roles = :roles")
    // List<Utilisateur> findActiveUsersByRoles(@Param("roles") String roles);


    UserDetails findByMail(String mail);

    @Query("SELECT u FROM Utilisateur u WHERE u.activer = true")
    List<Datas_list_membres> findAllByActiverTrue();

    @Query("SELECT u FROM Utilisateur u WHERE u.activer = true and roles = 'USER'")
    List<Datas_list_membres> findAllUser();


}