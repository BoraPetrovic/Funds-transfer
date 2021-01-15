package com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.model.Banka;
import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.model.TipRacuna;
import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.service.BankaService;
import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.service.TipRacunaService;
import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.support.BankaToBankaDto;
import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.support.TipRacunaToTipRacunaDto;
import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.web.dto.BankaDTO;
import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.web.dto.TipRacunaDTO;

@RestController
@RequestMapping(value = "/api/banke", produces = MediaType.APPLICATION_JSON_VALUE)
public class BankaController {

	@Autowired
    private BankaService bankaService;

	@Autowired
	private BankaToBankaDto toBankaDto;
	
	@Autowired
    private TipRacunaService tipRacunaService;

	@Autowired
	private TipRacunaToTipRacunaDto toTipRacunaDto;
	
	@PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
    @GetMapping
    public ResponseEntity<List<BankaDTO>> getAll(){
        
        List<Banka> entityPage = bankaService.findAll();
        
       return new ResponseEntity<>(toBankaDto.convert(entityPage), HttpStatus.OK);
    }
	
	@PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
    @GetMapping(value = "/{id}/tipovi-racuna")
    public ResponseEntity<List<TipRacunaDTO>> getAllByBanka(@PathVariable Long id){
        
        List<TipRacuna> entityPage = tipRacunaService.findAllByBankaId(id);
        
       return new ResponseEntity<>(toTipRacunaDto.convert(entityPage), HttpStatus.OK);
    }
}
