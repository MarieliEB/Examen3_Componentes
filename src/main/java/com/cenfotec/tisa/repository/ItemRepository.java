package com.cenfotec.tisa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cenfotec.tisa.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{

}
