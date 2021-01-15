package com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.model.Korisnik;
import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.repository.KorisnikRepository;
import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.service.KorisnikService;
import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.web.dto.KorisnikPromenaLozinkeDTO;

@Service
public class JpaKorisnikService implements KorisnikService{
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Korisnik findFirstByKorisnickoImeAndLozinka(String korisnickoIme, String lozinka) {
		// TODO Auto-generated method stub
		return korisnikRepository.findFirstByKorisnickoImeAndLozinka(korisnickoIme, lozinka);
	}

	@Override
	public Korisnik findFirstByKorisnickoIme(String korisnickoIme) {
		// TODO Auto-generated method stub
		return korisnikRepository.findFirstByKorisnickoIme(korisnickoIme);
	}

	@Override
	public Korisnik save(Korisnik korisnik) {
		// TODO Auto-generated method stub
		return korisnikRepository.save(korisnik);
	}

	@Override
	public List<Korisnik> findAll() {
		// TODO Auto-generated method stub
		return korisnikRepository.findAll();
	}

	@Override
	public Korisnik findById(Long id) {
		// TODO Auto-generated method stub
		return korisnikRepository.findById(id).get();
	}

	@Override
	public Korisnik delete(Long id) {
		Korisnik findOne = korisnikRepository.findById(id).get();
		if (findOne != null) {
			korisnikRepository.delete(findOne);
			return findOne;
		}
		return null;
	}

	@Override
	public Page<Korisnik> findAll(int pageNum) {
		// TODO Auto-generated method stub
		return korisnikRepository.findAll(PageRequest.of(pageNum, 5));
	}

	@Override
	public boolean changePassword(Long id, KorisnikPromenaLozinkeDTO korisnikPromenaLozinkeDto) {
		Optional<Korisnik> rezultat = korisnikRepository.findById(id);

        if(!rezultat.isPresent()) {
            throw new EntityNotFoundException();
        }

        Korisnik korisnik = rezultat.get();

        if(!korisnik.getKorisnickoIme().equals(korisnikPromenaLozinkeDto.getKorisnickoIme())
                || !korisnik.getLozinka().equals(korisnikPromenaLozinkeDto.getNovaLozinka())){
            return false;
        }

        // dodatak za zadatak 2
        String password = korisnikPromenaLozinkeDto.getNovaLozinka();
        if (!korisnikPromenaLozinkeDto.getNovaLozinka().equals("")) {
            password = passwordEncoder.encode(korisnikPromenaLozinkeDto.getNovaLozinka());
        }

        korisnik.setLozinka(password);

        korisnikRepository.save(korisnik);

        return true;
	}

	@Override
	public Optional<Korisnik> findByKorisnickoIme(String korisnickoIme) {
		// TODO Auto-generated method stub
		return korisnikRepository.findByKorisnickoIme(korisnickoIme);
	}

}
