package com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Banka {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	@NotBlank
	private String naziv;
	
	@Column(name = "sredstva_banke")
	private int sredstvaBanke;
	
	@OneToMany(mappedBy = "banka")
	private List<TipRacuna> tipoviRacuna;
	
	@OneToMany(mappedBy = "banka")
	private List<Racun> racuni;

	public Banka() {
		super();
	}

	public Banka(Long id, @NotBlank String naziv, int sredstvaBanke, List<TipRacuna> tipoviRacuna, List<Racun> racuni) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.sredstvaBanke = sredstvaBanke;
		this.tipoviRacuna = tipoviRacuna;
		this.racuni = racuni;
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

	public List<TipRacuna> getTipoviRacuna() {
		return tipoviRacuna;
	}

	public void setTipoviRacuna(List<TipRacuna> tipoviRacuna) {
		this.tipoviRacuna = tipoviRacuna;
	}
	
	public void dodajTipRacuna(TipRacuna tipRacuna){
        this.tipoviRacuna.add(tipRacuna);
        if(!equals(tipRacuna.getBanka())){
            tipRacuna.setBanka(this);
        }
    }

	public List<Racun> getRacuni() {
		return racuni;
	}

	public void setRacuni(List<Racun> racuni) {
		this.racuni = racuni;
	}
	
	public void dodajRacun(Racun racun){
        this.racuni.add(racun);
        if(!equals(racun.getBanka())){
            racun.setBanka(this);
        }
    }

	@Override
	public String toString() {
		return "Banka [id=" + id + ", naziv=" + naziv + ", sredstvaBanke=" + sredstvaBanke + ", tipoviRacuna="
				+ tipoviRacuna + ", racuni=" + racuni + "]";
	}
	
	
}
