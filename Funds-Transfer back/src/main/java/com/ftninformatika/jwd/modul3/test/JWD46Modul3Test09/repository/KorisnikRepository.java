package com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.model.Korisnik;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {
	
	Korisnik findFirstByKorisnickoImeAndLozinka (String korisnickoIme, String lozinka);
	Korisnik findFirstByKorisnickoIme (String korisnickoIme);
	Optional<Korisnik> findByKorisnickoIme(String korisnickoIme);
}
