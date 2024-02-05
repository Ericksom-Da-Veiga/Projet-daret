package com.example.daret.controlles;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.example.daret.dto.participation.Post_participation_dto;
import com.example.daret.dto.user.DataDetailMembres;
import com.example.daret.dto.user.Datas_list_membres;
import com.example.daret.dto.user.Update_user;
import com.example.daret.dto.user.User_DTO;
import com.example.daret.entite.Darets;
import com.example.daret.entite.Participation;
import com.example.daret.entite.Utilisateur;
import com.example.daret.repository.Darets_repository;
import com.example.daret.repository.Participation_repository;
import com.example.daret.repository.User_repository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.web.util.UriComponentsBuilder;




@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
	
	@Autowired 
	private User_repository repository;
	@Autowired
	private Participation_repository participation_repository;
	@Autowired
	private Darets_repository darets_repository;

	// -----------------------lES MEMBRES--------------------------------------------------
	@PostMapping("/membre")
	@Transactional
	public ResponseEntity<DataDetailMembres> register(@RequestBody @Valid User_DTO data, UriComponentsBuilder uriBuilder) {
		//testar se existe o mesmo email salvo no banco de dados
		if(this.repository.findByMail(data.mail()) != null){
			String errorMessage = "Email existe deja : le mail " + data.mail() + " est deja enregistre";
			return ResponseEntity.badRequest().body(DataDetailMembres.withError(errorMessage));
		}
			
		
		//para encripar o password do user
		String ecryptedPassword = new BCryptPasswordEncoder().encode(data.mot_passe()); 

		var membre = new Utilisateur(data,ecryptedPassword);
		
	  	repository.save(membre); 
		var uri = uriBuilder.path("/membre/{id}").buildAndExpand(membre.getId()).toUri();

		return ResponseEntity.created(uri).body(new DataDetailMembres(membre));
	}

	@PutMapping("/membre/reactiver/{id}")
	@Transactional
	public ResponseEntity<Void> reactiver(@PathVariable Long id) {
	  	var membre = repository.getReferenceById(id);
		membre.reactiver();

		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/membre")
	@Transactional 
	public ResponseEntity<DataDetailMembres> updateUser(@RequestBody @Valid Update_user dados){ 
		var membre = repository.getReferenceById(dados.id());
	  	membre.UpateInfo(dados); 

		return ResponseEntity.ok(new DataDetailMembres(membre));
	}
	  
	@GetMapping("/membre/all")  //lister tous les membres
	public ResponseEntity<List<Datas_list_membres>> ListUser(){ 
		var lista =  repository.findAllByActiverTrue(); 
		if(lista != null && !lista.isEmpty()){
            return ResponseEntity.ok(lista);
        }else
            return ResponseEntity.noContent().build();
	}

	@GetMapping("/membres")  //lister tous les membres
	public ResponseEntity<List<Datas_list_membres>> ListeMenbres(){ 
		var lista =  repository.findAllByActiverTrue(); 
		if(lista != null && !lista.isEmpty()){
            return ResponseEntity.ok(lista);
        }else
            return ResponseEntity.noContent().build();
	}

	@GetMapping("/membre/{id}")
	public ResponseEntity<DataDetailMembres> SearchMembre(@PathVariable Long id) {
		var membre = repository.getReferenceById(id);
		return ResponseEntity.ok(new DataDetailMembres(membre));
	}
	
	@DeleteMapping("/membre/delete/{id}")
	@Transactional
	public ResponseEntity<Void> supprimer(@PathVariable long id){
		var membre = repository.getReferenceById(id);
		membre.desativer();

		return ResponseEntity.noContent().build();
	}
	
	// --------------------------- lES PARTICIPATIONS -------------------------
	@PostMapping("/user/participer/{id_user}/{id_daret}")
	public ResponseEntity<Participation> registerParticipations(@PathVariable Long id_user,@PathVariable Long id_daret , @RequestBody @Valid Post_participation_dto data_participation) {

		int maximun_participant = darets_repository.getNumberMaximun(id_daret);

		int number_participant = participation_repository.getNumberParticipant(id_daret);

		if (maximun_participant<= number_participant)
			return ResponseEntity.noContent().build();

		int participation = this.participation_repository.findByIdmembreAndIddaret(id_user, id_daret);
		if( participation != 0){
			return ResponseEntity.badRequest().build();
		}
		 try {
       
			// Crie e salve a participação
        var participationEntity = new Participation(id_user, id_daret, data_participation);
        Participation savedParticipation = participation_repository.save(participationEntity);

        return ResponseEntity.ok(savedParticipation);
    	} catch (Exception e) {
        // Log ou trate a exceção conforme necessário
        e.printStackTrace(); // Apenas um exemplo; use um logger na produção
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    	}
			
	}
	//lister les darets dans lequels le utilisateur participe
	@GetMapping("/user/participations/{id_user}")
	public ResponseEntity<List<Darets>> getParticiaption(@PathVariable Long id_user){

		List<Object[]> result = participation_repository.findByIdmembre(id_user);

		List<Darets> daretsList = new ArrayList<>();

		for (Object[] row : result) {
			Darets darets = new Darets();
			darets.setId((Long) row[0]); // Coluna 0, ajuste conforme sua estrutura de tabela
			darets.setNom((String) row[1]); // Coluna 1, ajuste conforme sua estrutura de tabela
			darets.setDescription((String)row[2]);
			//converter o Date para o localDate
			Date sqlDate = (Date) row[3];
			darets.setDate_debut(sqlDate.toLocalDate());

			darets.setPeriodicite((int)row[4]);
			darets.setMaximun_participant((int)row[5]);
			darets.setActiver((boolean)row[6]);
			darets.setMontant((Long)row[7]);

			daretsList.add(darets);
		}

		return ResponseEntity.ok(daretsList);

	}
}