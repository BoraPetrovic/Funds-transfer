package com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.model.Racun;
import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.service.BankaService;
import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.service.RacunService;
import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.service.TipRacunaService;
import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.web.dto.RacunDTO;

@Component
public class RacunDtoToRacun implements Converter<RacunDTO, Racun>{
	
	@Autowired
	private RacunService racunService;
	
	@Autowired
	private BankaService bankaService;
	
	@Autowired
	private TipRacunaService tipRacunaService;

	@Override
	public Racun convert(RacunDTO source) {
		Racun entity;
		
		if (source.getId() != null) {
			entity = racunService.findOne(source.getId());
		} else {
			entity = new Racun();
		}
		
		if (entity != null) {
			entity.setBanka(bankaService.findOne(source.getBankaId()));
			entity.setBrRacuna(source.getBrRacuna());
			entity.setImeIPrezime(source.getImeIPrezime());
			entity.setJmbg(source.getJmbg());
			entity.setStanje(source.getStanje());
			entity.setTipRacuna(tipRacunaService.findOne(source.getTipRacunaId()));
		}
		return entity;
	}
	
}
