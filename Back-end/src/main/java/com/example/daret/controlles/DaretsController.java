package com.example.daret.controlles;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.example.daret.dto.darets.DaretsDetail;
import com.example.daret.dto.darets.PostDaret_DTO;
import com.example.daret.dto.darets.Liste_darets_dto;
import com.example.daret.entite.Darets;
import com.example.daret.repository.Darets_repository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/darets")
public class DaretsController {

    @Autowired
    Darets_repository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DaretsDetail> CreateDarets(@RequestBody @Valid PostDaret_DTO data, UriComponentsBuilder uriBuilder) 
    {
        var daret = new Darets(data);
        repository.save(daret);

        var uri = uriBuilder.path("/darets/{id}").buildAndExpand(daret.getId()).toUri();
        return ResponseEntity.created(uri).body(new DaretsDetail(daret));
    }

	@GetMapping
	public ResponseEntity<List<Liste_darets_dto>> ListDarets(){ 
		List<Liste_darets_dto> lista =  repository.findAllByActiverTrue();
        if(lista != null && !lista.isEmpty()){
            return ResponseEntity.ok(lista);
        }else
            return ResponseEntity.noContent().build();
	}

    @GetMapping("/{id}")
    public ResponseEntity<DaretsDetail> searchByid(@PathVariable Long id){
        var daret = repository.getReferenceById(id);

        return ResponseEntity.ok(new DaretsDetail(daret));
    }
    

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<Void> DeleteDarets(@PathVariable Long id){
        var daret = repository.getReferenceById(id);
        daret.desativer();

        return ResponseEntity.noContent().build();
    }
    
}