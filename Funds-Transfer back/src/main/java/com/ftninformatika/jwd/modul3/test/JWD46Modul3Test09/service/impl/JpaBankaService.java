package com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.model.Banka;
import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.repository.BankaRepository;
import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.service.BankaService;

@Service
public class JpaBankaService implements BankaService{

	@Autowired
    private BankaRepository bankaRepository;
	
	@Override
	public List<Banka> findAll() {
		// TODO Auto-generated method stub
		return bankaRepository.findAll();
	}

	@Override
	public Banka findOne(Long id) {
		// TODO Auto-generated method stub
		return bankaRepository.findById(id).get();
	}

}
