package com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.enumeracija.KorisnickaUloga;

@Entity
public class Korisnik {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "e_mail")
	private String eMail;
	
	@Column(name = "korisnicko_ime")
	private String korisnickoIme;
	
	@Column
	private String lozinka;
	
	@Column
	private String ime;
	
	@Column
	private String prezime;
	
	@Enumerated(EnumType.STRING)
	private KorisnickaUloga uloga;

	public Korisnik() {
		super();
	}

	public Korisnik(Long id, String eMail, String korisnickoIme, String lozinka, String ime, String prezime,
			KorisnickaUloga uloga) {
		super();
		this.id = id;
		this.eMail = eMail;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prezime;
		this.uloga = uloga;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
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

	public KorisnickaUloga getUloga() {
		return uloga;
	}

	public void setUloga(KorisnickaUloga uloga) {
		this.uloga = uloga;
	}

	@Override
	public String toString() {
		return "Korisnik [id=" + id + ", eMail=" + eMail + ", korisnickoIme=" + korisnickoIme + ", lozinka=" + lozinka
				+ ", ime=" + ime + ", prezime=" + prezime + ", uloga=" + uloga + "]";
	}
	
	
}
