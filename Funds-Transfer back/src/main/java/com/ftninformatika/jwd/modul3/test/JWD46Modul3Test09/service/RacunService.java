package com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.service;

import org.springframework.data.domain.Page;

import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.model.Racun;

public interface RacunService {

	Page<Racun> findAll(String jmbg, Long bankaId, int pageNum);
	Racun findOne(Long id);
	Racun save(Racun racun);
	Racun update(Racun racun);
	Racun delete(Long id);
	boolean prenos(String uplatioc, String primaoc, Integer iznos);
}
