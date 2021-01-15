package com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.model.Korisnik;
import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.service.KorisnikService;
import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.web.dto.KorisnikDTO;

@Component
public class KorisnikDtoToKorisnik implements Converter<KorisnikDTO, Korisnik>{
	
	@Autowired
	private KorisnikService korisnikService;

	@Override
	public Korisnik convert(KorisnikDTO source) {
		Korisnik entity;
		
		if (source.getId() != null) {
			entity = korisnikService.findById(source.getId());
		} else {
			entity = new Korisnik();
		}
		
		if (entity != null) {
			entity.seteMail(source.getEmail());
			entity.setIme(source.getIme());
			entity.setKorisnickoIme(source.getKorisnickoIme());
			entity.setPrezime(source.getPrezime());
		}
		return entity;
	}
	
}
