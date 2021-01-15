package com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.model.TipRacuna;
import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.web.dto.TipRacunaDTO;

@Component
public class TipRacunaToTipRacunaDto implements Converter<TipRacuna, TipRacunaDTO>{

	@Override
	public TipRacunaDTO convert(TipRacuna source) {
		TipRacunaDTO dto = new TipRacunaDTO();
		dto.setBankaId(source.getBanka().getId());
		dto.setId(source.getId());
		dto.setBankaNaziv(source.getBanka().getNaziv());
		dto.setNaziv(source.getNaziv());
		dto.setProvizija(source.getProvizija());
		return dto;
	}
	
	public List<TipRacunaDTO> convert(List<TipRacuna> lista){
		List<TipRacunaDTO> listaDTO = new ArrayList<TipRacunaDTO>();
		for (TipRacuna entity : lista) {
			listaDTO.add(convert(entity));
		}
		return listaDTO;
	}

}
