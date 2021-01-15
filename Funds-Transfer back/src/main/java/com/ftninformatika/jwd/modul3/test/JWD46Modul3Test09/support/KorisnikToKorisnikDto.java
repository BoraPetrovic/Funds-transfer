package com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.model.Korisnik;
import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.web.dto.KorisnikDTO;

@Component
public class KorisnikToKorisnikDto implements Converter<Korisnik, KorisnikDTO>{

	@Override
	public KorisnikDTO convert(Korisnik source) {
		KorisnikDTO dto = new KorisnikDTO();
		dto.setEmail(source.geteMail());
		dto.setId(source.getId());
		dto.setIme(source.getIme());
		dto.setKorisnickoIme(source.getKorisnickoIme());
		dto.setPrezime(source.getPrezime());
		return dto;
	}
	
	public List<KorisnikDTO> convert(List<Korisnik> lista){
		List<KorisnikDTO> listaDTO = new ArrayList<KorisnikDTO>();
		for (Korisnik entity : lista) {
			listaDTO.add(convert(entity));
		}
		return listaDTO;
	}

}
