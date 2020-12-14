package com.cenfotec.tisa.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cenfotec.tisa.model.Cliente;
import com.cenfotec.tisa.model.Item;
import com.cenfotec.tisa.model.Orden;
import com.cenfotec.tisa.repository.ClienteRepository;
import com.cenfotec.tisa.repository.ItemRepository;
import com.cenfotec.tisa.repository.OrdenRepository;



@RestController
@RequestMapping({"/orden"})
public class OrdenController {
	
	private OrdenRepository ordenRepository;
	private ClienteRepository clienteRepository;
	private ItemRepository itemRepository;
	
	OrdenController(OrdenRepository ordenRepository, ClienteRepository clienteRepository, ItemRepository itemRepository) {
		 this.ordenRepository = ordenRepository;
		 this.clienteRepository = clienteRepository;
		 this.itemRepository = itemRepository;
		 }
	
	@GetMapping
	public List<Orden> findAll(){
		return ordenRepository.findAll();
	}
	
	@GetMapping(path = {"/{item}"})
	public List<Orden> findById(@PathVariable String item){
		List<Orden> ordenesFiltradas = new ArrayList<Orden>();
		List<Orden> ordenes = ordenRepository.findAll();
		for(Orden orden: ordenes) {
			if(item.toLowerCase().equals(orden.getItem().toLowerCase())) {
				ordenesFiltradas.add(orden);
			}
		}
		
		return ordenesFiltradas;
	}
	
	@PostMapping
	public ResponseEntity<Orden> create(@RequestBody Orden orden){
		boolean itemExiste = itemExiste(orden.getItem());
		boolean clienteExiste = clienteExiste(orden.getCliente());
		
		if(clienteExiste  == false || itemExiste == false) {
			
			return new ResponseEntity<Orden>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
			Orden ordenCreada = ordenRepository.save(orden);
			return new ResponseEntity<Orden>(ordenCreada, HttpStatus.OK);
	}
	
	public boolean itemExiste(String nombreItem) {
		boolean itemExiste = false;
		
		List<Item> items = itemRepository.findAll();
		
		for(Item item: items) {
			if(item.getNombre().equals(nombreItem)) {
				itemExiste = true;
			}
		}
		
		return itemExiste;
	}
	
	public boolean clienteExiste(int cliente) {
		boolean clienteExiste = true;
		
		long idCliente = cliente;
		Cliente clienteExistente = new Cliente();
		
		clienteExistente = clienteRepository.findById(idCliente).orElse(null);
		
		if( clienteExistente == null || clienteExistente.getId() != cliente) {
			clienteExiste = false;
		}
		
		return clienteExiste;
	}
	
	@PutMapping(value="/{id}")
	 public ResponseEntity<Orden> update(@PathVariable("id") long id, @RequestBody Orden orden){
		return ordenRepository.findById(id).map(record -> {
		 record.setCantidad(orden.getCantidad());
		 record.setItem(orden.getItem());
		 Orden updated = ordenRepository.save(record);
		 return ResponseEntity.ok().body(updated);
		 }).orElse(ResponseEntity.notFound().build());
	 }
}
