package com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftninformatika.jwd.modul3.test.JWD46Modul3Test09.model.Banka;

@Repository
public interface BankaRepository extends JpaRepository<Banka, Long> {

}
