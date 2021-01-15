package com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.model.Banka;
import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.service.BankaService;
import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.web.dto.BankaDTO;

@Component
public class BankaDtoToBanka implements Converter<BankaDTO, Banka>{
	
	@Autowired
	private BankaService bankaService;

	@Override
	public Banka convert(BankaDTO source) {
		Banka entity;
		
		if (source.getId() != null) {
			entity = bankaService.findOne(source.getId());
		} else {
			entity = new Banka();
		}
		
		if (entity != null) {
			entity.setNaziv(source.getNaziv());
			entity.setSredstvaBanke(source.getSredstvaBanke());
		}
		return entity;
	}
	
}
