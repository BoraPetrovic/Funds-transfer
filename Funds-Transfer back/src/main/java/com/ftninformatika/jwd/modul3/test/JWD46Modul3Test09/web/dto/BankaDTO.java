package com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.web.dto;

import javax.validation.constraints.NotBlank;

public class BankaDTO {

	private Long id;
	
	@NotBlank
	private String naziv;
	
	private int sredstvaBanke;

	public BankaDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public int getSredstvaBanke() {
		return sredstvaBanke;
	}

	public void setSredstvaBanke(int sredstvaBanke) {
		this.sredstvaBanke = sredstvaBanke;
	}
	
	
}
