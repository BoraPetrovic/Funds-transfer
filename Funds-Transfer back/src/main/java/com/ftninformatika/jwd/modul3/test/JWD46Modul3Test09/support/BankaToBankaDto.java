package com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.model.Banka;
import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.web.dto.BankaDTO;

@Component
public class BankaToBankaDto implements Converter<Banka, BankaDTO>{

	@Override
	public BankaDTO convert(Banka source) {
		BankaDTO dto = new BankaDTO();
		dto.setNaziv(source.getNaziv());
		dto.setId(source.getId());
		dto.setSredstvaBanke(source.getSredstvaBanke());
		return dto;
	}
	
	public List<BankaDTO> convert(List<Banka> lista){
		List<BankaDTO> listaDTO = new ArrayList<BankaDTO>();
		for (Banka entity : lista) {
			listaDTO.add(convert(entity));
		}
		return listaDTO;
	}

}
