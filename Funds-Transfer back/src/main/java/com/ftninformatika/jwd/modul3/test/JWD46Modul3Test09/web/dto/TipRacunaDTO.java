package com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.web.dto;

import javax.validation.constraints.NotBlank;

public class TipRacunaDTO {

	private Long id;
	
	@NotBlank
	private String naziv;
	
	private int provizija;
	
	private Long bankaId;
	private String bankaNaziv;
	public TipRacunaDTO() {
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
	public int getProvizija() {
		return provizija;
	}
	public void setProvizija(int provizija) {
		this.provizija = provizija;
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
	
	
}
