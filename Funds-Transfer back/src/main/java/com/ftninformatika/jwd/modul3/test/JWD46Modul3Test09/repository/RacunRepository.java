package com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.model.Racun;

@Repository
public interface RacunRepository extends JpaRepository<Racun, Long> {

	@Query("SELECT r FROM Racun r WHERE "
			+"(:jmbg IS NULL OR r.jmbg = :jmbg) AND "
			+"(:bankaId IS NULL OR r.banka.id = :bankaId)")
	Page<Racun> findAll(@Param("jmbg") String jmbg, @Param("bankaId") Long bankaId, Pageable pageable);
}
