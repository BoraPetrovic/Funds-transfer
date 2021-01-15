package com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.model.Racun;
import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.web.dto.RacunDTO;

@Component
public class RacunToRacunDto implements Converter<Racun, RacunDTO>{

	@Override
	public RacunDTO convert(Racun source) {
		RacunDTO dto = new RacunDTO();
		dto.setBankaId(source.getBanka().getId());
		dto.setId(source.getId());
		dto.setBankaNaziv(source.getBanka().getNaziv());
		dto.setBrRacuna(source.getBrRacuna());
		dto.setImeIPrezime(source.getImeIPrezime());
		dto.setJmbg(source.getJmbg());
		dto.setStanje(source.getStanje());
		dto.setTipRacunaId(source.getTipRacuna().getId());
		dto.setTipRacunaNaziv(source.getTipRacuna().getNaziv());
		return dto;
	}
	
	public List<RacunDTO> convert(List<Racun> lista){
		List<RacunDTO> listaDTO = new ArrayList<RacunDTO>();
		for (Racun entity : lista) {
			listaDTO.add(convert(entity));
		}
		return listaDTO;
	}

}
