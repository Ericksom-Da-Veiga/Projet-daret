package com.example.daret.controlles;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.example.daret.dto.payements.List_payements_dto;
import com.example.daret.dto.payements.Post_Payement_dto;
import com.example.daret.entite.Darets;
import com.example.daret.entite.Payements;
import com.example.daret.repository.Payements_repository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
public class PayementController {

    @Autowired
    Payements_repository repository;

    @PostMapping("/payements/{id_admin}")
    @Transactional
    public ResponseEntity<Detail_Payement_dto> PostPayement(@PathVariable Long id_admin,@RequestBody @Valid Post_Payement_dto payement_dto,UriComponentsBuilder uriBuilder){
        Payements payements = new Payements(id_admin, payement_dto);
        repository.save(payements);

        var uri = uriBuilder.path("/payements/{id}").buildAndExpand(payements.getId()).toUri();

        return ResponseEntity.created(uri).body(new Detail_Payement_dto(payements));
    }

    @GetMapping("/payements")
    public ResponseEntity<List<List_payements_dto>> getPayements(){
        List<Object[]> result = repository.findAllPayements();

        List<List_payements_dto> lista = new ArrayList<>();

        for (Object[] row : result) {
			String nom_membre =((String) row[0]);
            String nom_daret =((String) row[1]);
            Date sqlDate =((Date) row[2]);
            LocalDate date_payement = (sqlDate.toLocalDate());
            String nom_admin =((String) row[3]);

            List_payements_dto payements_dto = new List_payements_dto(nom_membre, nom_daret, date_payement, nom_admin);

            lista.add(payements_dto);
		}

        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
            
        return ResponseEntity.ok(lista);
    }
}