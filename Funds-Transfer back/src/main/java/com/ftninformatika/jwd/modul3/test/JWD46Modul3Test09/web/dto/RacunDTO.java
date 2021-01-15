package com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.web.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RacunDTO {

	private Long id;
	
	@NotBlank
	private String imeIPrezime;
	
	@Size(min = 13, max = 13)
	private String jmbg;
	
	@NotBlank
	private String brRacuna;
	
	private int stanje;
	
	private Long bankaId;
	private String bankaNaziv;
	
	private Long tipRacunaId;
	private String tipRacunaNaziv;
	
	public RacunDTO() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getImeIPrezime() {
		return imeIPrezime;
	}
	public void setImeIPrezime(String imeIPrezime) {
		this.imeIPrezime = imeIPrezime;
	}
	public String getJmbg() {
		return jmbg;
	}
	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}
	public String getBrRacuna() {
		return brRacuna;
	}
	public void setBrRacuna(String brRacuna) {
		this.brRacuna = brRacuna;
	}
	public int getStanje() {
		return stanje;
	}
	public void setStanje(int stanje) {
		this.stanje = stanje;
	}

	public Long getBankaId() {
		return bankaId;
	}
	public void setBankaId(Long bankaId) {
		this.bankaId = bankaId;
	}
	public String getBankaNaziv() {
		return bankaNaziv;
	}
	public void setBankaNaziv(String bankaNaziv) {
		this.bankaNaziv = bankaNaziv;
	}
	public Long getTipRacunaId() {
		return tipRacunaId;
	}
	public void setTipRacunaId(Long tipRacunaId) {
		this.tipRacunaId = tipRacunaId;
	}
	public String getTipRacunaNaziv() {
		return tipRacunaNaziv;
	}
	public void setTipRacunaNaziv(String tipRacunaNaziv) {
		this.tipRacunaNaziv = tipRacunaNaziv;
	}
	
	
	
}
