package com.example.daret.entite;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.daret.dto.user.Update_user;
import com.example.daret.dto.user.User_DTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;  
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor; 
import lombok.Setter;

@Entity
@Table(name = "utilisateur")
@Getter
@Setter
@AllArgsConstructor //constructor com todos os
@NoArgsConstructor//constructor vazio
@EqualsAndHashCode(of = "id")

public class Utilisateur implements UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String prenom;
	private String mail;
	private String mot_passe;
	private LocalDate date_inscription;
	private Boolean activer;
	private String roles;

	
	//constructor com parametros 
	public Utilisateur(User_DTO data, String password) {
		this.nom = data.nom();
		this.prenom = data.prenom();
		this.mail = data.mail();
		this.mot_passe = password;
		this.activer = true;
		this.roles = data.roles();
		this.date_inscription = data.date_inscription();
	}
	
	public void UpateInfo(@Valid Update_user data) {
		
		if(data.nom() != null) {
			this.nom = data.nom();
		}
		if(data.prenom() != null) {
			this.prenom = data.prenom();
		}
		if(data.mail() != null) {
			this.mail = data.mail();
		}
		
	}

	public void desativer(){
		this.activer = false;
	}

    public void reactiver() {
		this.activer = true;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(this.roles.equalsIgnoreCase("ADMIN")) 
			return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN") );
		else 
			return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getPassword() {
		return mot_passe;
	}

	@Override
	public String getUsername() {
		return mail;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}	
}