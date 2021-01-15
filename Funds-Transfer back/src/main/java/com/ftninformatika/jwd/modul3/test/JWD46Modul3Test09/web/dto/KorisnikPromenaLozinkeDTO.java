package com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.web.dto;

import javax.validation.constraints.Pattern;

public class KorisnikPromenaLozinkeDTO {
	
	private String korisnickoIme;
	
	private String staraLozinka;
	
	@Pattern(regexp = "^(?=.*[A-Z].*[A-Z])(?=.*[!@#$&*])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z].*[a-z]).{8}$")
	private String novaLozinka;
	
	private String ponovljenaLozinka;

	public KorisnikPromenaLozinkeDTO() {
		super();
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getStaraLozinka() {
		return staraLozinka;
	}

	public void setStaraLozinka(String staraLozinka) {
		this.staraLozinka = staraLozinka;
	}

	public String getNovaLozinka() {
		return novaLozinka;
	}

	public void setNovaLozinka(String novaLozinka) {
		this.novaLozinka = novaLozinka;
	}

	public String getPonovljenaLozinka() {
		return ponovljenaLozinka;
	}

	public void setPonovljenaLozinka(String ponovljenaLozinka) {
		this.ponovljenaLozinka = ponovljenaLozinka;
	}
	
	
	
}
