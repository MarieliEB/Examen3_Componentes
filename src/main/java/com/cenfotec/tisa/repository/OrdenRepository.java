package com.cenfotec.tisa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cenfotec.tisa.model.Orden;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Long> {

}
