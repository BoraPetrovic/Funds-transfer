package com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.model.Korisnik;
import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.web.dto.KorisnikPromenaLozinkeDTO;

public interface KorisnikService {
	
	Korisnik findFirstByKorisnickoImeAndLozinka (String korisnickoIme, String lozinka);
	Korisnik findFirstByKorisnickoIme (String korisnickoIme);	
	Korisnik save(Korisnik korisnik);	
	List<Korisnik> findAll();
	Korisnik findById(Long Id);	
	Korisnik delete (Long id);
	Page<Korisnik> findAll(int pageNum);
	Optional<Korisnik> findByKorisnickoIme(String korisnickoIme);
	boolean changePassword(Long id, KorisnikPromenaLozinkeDTO korisnikPromenaLozinkeDto);
}
