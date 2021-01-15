package com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.model.TipRacuna;
import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.repository.TipRacunaRepository;
import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.service.TipRacunaService;

@Service
public class JpaTipRacunaService implements TipRacunaService{

	@Autowired
    private TipRacunaRepository tipRacunaRepository;
	
	@Override
	public List<TipRacuna> findAllByBankaId(Long id) {
		// TODO Auto-generated method stub
		return tipRacunaRepository.findAllByBankaId(id);
	}

	@Override
	public TipRacuna findOne(Long id) {
		// TODO Auto-generated method stub
		return tipRacunaRepository.findById(id).get();
	}

}
