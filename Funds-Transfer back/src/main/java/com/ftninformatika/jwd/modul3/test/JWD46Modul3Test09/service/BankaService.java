package com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.service;

import java.util.List;

import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.model.Banka;

public interface BankaService {

	List<Banka> findAll();
	Banka findOne(Long id);
}
