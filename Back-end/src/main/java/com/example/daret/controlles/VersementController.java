package com.example.daret.controlles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.example.daret.dto.versement.Adminreturn;
import com.example.daret.dto.versement.Get_versement_dto;
import com.example.daret.dto.versement.Post_Versement_dto;
import com.example.daret.dto.versement.Versement_return_dto;
import com.example.daret.entite.Versement;
import com.example.daret.repository.Versement_repository;
import jakarta.validation.Valid;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class VersementController {

    @Autowired
    Versement_repository repository;

    @PostMapping("/versement")
    public ResponseEntity<Get_versement_dto> postVersement(@RequestBody @Valid Post_Versement_dto data, UriComponentsBuilder uriBuilder){

        Versement_return_dto user = repository.FindUserVersement(data.id_daret(), data.id_daret());

        Versement versement = new Versement(data,user.id());
        repository.save(versement);

        var uri = uriBuilder.path("/versement/{id}").buildAndExpand(versement.getId()).toUri();
        return ResponseEntity.created(uri).body(new Get_versement_dto(versement));
    }

    @GetMapping("/versement/admin")
    public  ResponseEntity<List<Adminreturn>> getAdmin() {
        List<Adminreturn> adminList = repository.findAdminMapped();
        return ResponseEntity.ok(adminList);
    }

    
    @GetMapping("/versement/{id_daret}")
    public ResponseEntity<Versement_return_dto> GetProchoineUser(@PathVariable Long id_daret){
        

        Versement_return_dto user = repository.FindUserVersement(id_daret, id_daret);
        return ResponseEntity.ok(user);
    }
}
