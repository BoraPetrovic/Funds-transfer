package com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.web.dto;

public class KorisnikRegistracijaDTO extends KorisnikDTO {
	
	private String lozinka;
	
	private String ponovljenaLozinka;

	public KorisnikRegistracijaDTO() {
		super();
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getPonovljenaLozinka() {
		return ponovljenaLozinka;
	}

	public void setPonovljenaLozinka(String ponovljenaLozinka) {
		this.ponovljenaLozinka = ponovljenaLozinka;
	}
	
	
}
