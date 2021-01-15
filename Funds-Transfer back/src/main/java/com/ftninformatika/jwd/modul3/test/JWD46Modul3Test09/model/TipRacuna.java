package com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tip_racuna")
public class TipRacuna {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	@NotBlank
	private String naziv;
	
	@Column
	private int provizija;
	
	@ManyToOne
	private Banka banka;
	
	@OneToMany(mappedBy = "tipRacuna")
	private List<Racun> racuni;

	public TipRacuna() {
		super();
	}

	public TipRacuna(Long id, @NotBlank String naziv, int provizija, Banka banka) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.provizija = provizija;
		this.banka = banka;
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

	public Banka getBanka() {
		return banka;
	}

	public void setBanka(Banka banka) {
		this.banka = banka;
		if(banka !=null && !banka.getTipoviRacuna().contains(this)){
            banka.getTipoviRacuna().add(this);
        }
	}
	
	public void dodajRacun(Racun racun){
        this.racuni.add(racun);
        if(!equals(racun.getTipRacuna())){
            racun.setTipRacuna(this);
        }
    }

	public List<Racun> getRacuni() {
		return racuni;
	}

	public void setRacuni(List<Racun> racuni) {
		this.racuni = racuni;
	}

	@Override
	public String toString() {
		return "TipRacuna [id=" + id + ", naziv=" + naziv + ", provizija=" + provizija + ", banka=" + banka + "]";
	}
	
	
}
