package com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.service;

import java.util.List;

import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.model.TipRacuna;

public interface TipRacunaService {

	List<TipRacuna> findAllByBankaId(Long id);
	TipRacuna findOne(Long id);
}
