package com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.model.Racun;
import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.service.RacunService;
import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.support.RacunDtoToRacun;
import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.support.RacunToRacunDto;
import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.web.dto.RacunDTO;

@RestController
@RequestMapping(value = "/api/racuni", produces = MediaType.APPLICATION_JSON_VALUE)
public class RacunController {


    @Autowired
    private RacunService racunService;

    @Autowired
    private RacunDtoToRacun toRacun;

    @Autowired
    private RacunToRacunDto toRacunDto;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RacunDTO> create(@Valid @RequestBody RacunDTO entityDTO){
    	Racun entity = toRacun.convert(entityDTO);
        
    	Racun sacuvanEntity = racunService.save(entity);

        return new ResponseEntity<>(toRacunDto.convert(sacuvanEntity), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value= "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RacunDTO> update(@PathVariable Long id, @Valid @RequestBody RacunDTO entityDTO){

        if(!id.equals(entityDTO.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Racun entity = toRacun.convert(entityDTO);

        Racun sacuvanEntity = racunService.update(entity);

        return new ResponseEntity<>(toRacunDto.convert(sacuvanEntity),HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/prenos")
    public ResponseEntity<Void> prenos(
    		@RequestParam(required=false) String uplatioc,
    		@RequestParam(required=false) String primalac,
    		@RequestParam(required=false) Integer iznos){
    	
    	System.out.println(iznos + " lalallalalalal");

        if(iznos != null) {
        	boolean prenos =  racunService.prenos(uplatioc, primalac, iznos);
        	if(prenos) {
        		return new ResponseEntity<>(HttpStatus.OK);
        	} else {
        		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
			}
        	
        } else {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

        
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
    	Racun obrisanEntity = racunService.delete(id);

        if(obrisanEntity != null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<RacunDTO> getOne(@PathVariable Long id){
    	Racun entity = racunService.findOne(id);

        if(entity != null) {
            return new ResponseEntity<>(toRacunDto.convert(entity), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
    @GetMapping
    public ResponseEntity<List<RacunDTO>> getAll(
    		@RequestParam(required=false) String jmbg,
            @RequestParam(required=false) Long bankaId,
            @RequestParam(required=false) String tip,
            @RequestParam(defaultValue = "0") int pageNum){
        
        Page<Racun> entityPage = racunService.findAll(jmbg, bankaId, pageNum);
        
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Total-Pages", entityPage.getTotalPages() + "");
        
       return new ResponseEntity<>(toRacunDto.convert(entityPage.getContent()), responseHeaders, HttpStatus.OK);
    }
}
