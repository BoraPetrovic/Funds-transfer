package com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.model.TipRacuna;
import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.service.BankaService;
import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.service.TipRacunaService;
import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.web.dto.TipRacunaDTO;

@Component
public class TipRacunaDtoToTipRacuna implements Converter<TipRacunaDTO, TipRacuna>{
	
	@Autowired
	private TipRacunaService tipRacunaService;
	
	@Autowired
	private BankaService bankaService;

	@Override
	public TipRacuna convert(TipRacunaDTO source) {
		TipRacuna entity;
		
		if (source.getId() != null) {
			entity = tipRacunaService.findOne(source.getId());
		} else {
			entity = new TipRacuna();
		}
		
		if (entity != null) {
			entity.setBanka(bankaService.findOne(source.getBankaId()));
			entity.setNaziv(source.getNaziv());
			entity.setProvizija(source.getProvizija());
		}
		return entity;
	}
	
}
