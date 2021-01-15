package com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.web.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class KorisnikDTO {
	
	private Long id;
	
	@NotEmpty
    @Email
	private String email;
	
	@NotBlank
	private String korisnickoIme;
	
	@Size(min=3, max=50)
	private String ime;
	
	@Size(min=3, max=50)
	private String prezime;

	public KorisnikDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	
	
}
