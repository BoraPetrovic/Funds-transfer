package com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.model.Racun;
import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.repository.RacunRepository;
import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.service.RacunService;

@Service
public class JpaRacunService implements RacunService{

	@Autowired
    private RacunRepository racunRepository;
	
	@Override
	public Page<Racun> findAll(String jmbg, Long bankaId, int pageNum) {
		// TODO Auto-generated method stub
		return racunRepository.findAll(jmbg, bankaId, PageRequest.of(pageNum, 4));
	}

	@Override
	public Racun findOne(Long id) {
		// TODO Auto-generated method stub
		return racunRepository.findById(id).get();
	}

	@Override
	public Racun save(Racun racun) {
		// TODO Auto-generated method stub
		return racunRepository.save(racun);
	}

	@Override
	public Racun update(Racun racun) {
		// TODO Auto-generated method stub
		return racunRepository.save(racun);
	}

	@Override
	public Racun delete(Long id) {
		Racun delete = racunRepository.findById(id).get();
		if (delete != null) {
			racunRepository.delete(delete);
			return delete;
		}
		return null;
	}

	@Override
	public boolean prenos(String uplatioc, String primaoc, Integer iznos) {
		List<Racun> racuni = racunRepository.findAll();
		Racun uplatioc1 = null;
		Racun primaoc1 = null;
		for (Racun racun : racuni) {
			if (racun.getBrRacuna().equals(uplatioc)) {
				uplatioc1 = racun;
			} else if (racun.getBrRacuna().equals(primaoc)) {
				primaoc1 = racun;
			}
		}
		
		int provizija = uplatioc1.getTipRacuna().getProvizija();
		
		int sredstvaBanke = iznos/100 * provizija;
		if (sredstvaBanke > 1000) {
			sredstvaBanke = 1000;
		}
		
		int uplata = iznos + sredstvaBanke;
		
		if (uplatioc1.getStanje() < uplata) {
			return false;
		}
		
		uplatioc1.setStanje(uplatioc1.getStanje() - uplata);
		uplatioc1.getBanka().setSredstvaBanke(uplatioc1.getBanka().getSredstvaBanke() + sredstvaBanke);
		racunRepository.save(uplatioc1);
		
		primaoc1.setStanje(primaoc1.getStanje() + iznos);
		racunRepository.save(primaoc1);
		
		return true;
	}

}
