package com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Racun {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "ime_prezime")
	@NotBlank
	private String imeIPrezime;
	
	@Column
	private String jmbg;
	
	@Column(name = "br_racuna")
	private String brRacuna;
	
	@Column
	private int stanje;
	
	@ManyToOne
	private Banka banka;
	
	@ManyToOne
	private TipRacuna tipRacuna;

	public Racun() {
		super();
	}

	public Racun(Long id, @NotBlank String imeIPrezime, String jmbg, String brRacuna, int stanje, Banka banka) {
		super();
		this.id = id;
		this.imeIPrezime = imeIPrezime;
		this.jmbg = jmbg;
		this.brRacuna = brRacuna;
		this.stanje = stanje;
		this.banka = banka;
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

	public Banka getBanka() {
		return banka;
	}

	public void setBanka(Banka banka) {
		this.banka = banka;
		if(banka !=null && !banka.getRacuni().contains(this)){
            banka.getRacuni().add(this);
        }
	}

	public TipRacuna getTipRacuna() {
		return tipRacuna;
	}

	public void setTipRacuna(TipRacuna tipRacuna) {
		this.tipRacuna = tipRacuna;
		if(tipRacuna !=null && !tipRacuna.getRacuni().contains(this)){
			tipRacuna.getRacuni().add(this);
        }
	}

	@Override
	public String toString() {
		return "Racun [id=" + id + ", imeIPrezime=" + imeIPrezime + ", jmbg=" + jmbg + ", brRacuna=" + brRacuna
				+ ", stanje=" + stanje + ", banka=" + banka + "]";
	}
	
	
}
