package com.cenfotec.tisa.controllers;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cenfotec.tisa.model.Item;
import com.cenfotec.tisa.repository.ItemRepository;

@RestController
@RequestMapping({"/item"})
public class ItemController {
	
	private ItemRepository itemRepository;
	
	ItemController(ItemRepository itemRepository) {
		 this.itemRepository = itemRepository;
		 }
	
	@GetMapping
	public List<Item> findAll(){
		return itemRepository.findAll();
	}
	
	@PostMapping
	public String create(@RequestBody List<Item> items){
		 for (Item item : items) {
		       itemRepository.save(item);
		    }
		 return "Items registrados con éxito";
	}
}
