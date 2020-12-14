package com.cenfotec.tisa.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cenfotec.tisa.model.Cliente;
import com.cenfotec.tisa.model.Item;
import com.cenfotec.tisa.model.Orden;
import com.cenfotec.tisa.repository.ClienteRepository;
import com.cenfotec.tisa.repository.OrdenRepository;

@RestController
@RequestMapping({"/cliente"})
public class ClienteController {

	private ClienteRepository clienteRepository;
	private OrdenRepository ordenRepository;
	
	ClienteController(ClienteRepository clienteRepository, OrdenRepository ordenRepository) {
		 this.clienteRepository = clienteRepository;
		 this.ordenRepository = ordenRepository;
		 }
	
	@GetMapping
	public List<Cliente> findAll(){
		return clienteRepository.findAll();
	}
	
	@GetMapping(path = {"/{id}"})
	public ResponseEntity<Cliente> findById(@PathVariable long id){
		return clienteRepository.findById(id).map(record ->ResponseEntity.ok().body(record)).orElse(ResponseEntity.notFound().build());
	}
	
	
	@GetMapping(path = {"aproxApellido/{apellidoUno}"})
	public List<Cliente> findById(@PathVariable("apellidoUno") String apellidoUno){
		List<Cliente> clientesFiltrados = new ArrayList<Cliente>();
		List<Cliente> clientes = clienteRepository.findAll();
		
		for(Cliente cliente: clientes) {
			String sSubCadena = cliente.getApellidoUno().substring(0,apellidoUno.length());
			if(sSubCadena.toLowerCase().equals(apellidoUno.toLowerCase())) {
				clientesFiltrados.add(cliente);
			}
		}
		return clientesFiltrados;
	}
	
	
	@GetMapping(path = {"aproxDireccionCobro/{dirCobro}"})
	public List<Cliente> buscarAproximacionCobro(@PathVariable("dirCobro") String dirCobro){
		List<Cliente> clientesFiltrados = new ArrayList<Cliente>();
		List<Cliente> clientes = clienteRepository.findAll();
		
		for(Cliente cliente: clientes) {
			String sSubCadena = cliente.getDireccionCobro().substring(0,dirCobro.length());
			if(sSubCadena.toLowerCase().equals(dirCobro.toLowerCase())) {
				clientesFiltrados.add(cliente);
			}
		}
		return clientesFiltrados;
	}
	
	@PostMapping
	public Cliente create(@RequestBody Cliente cliente){
		return clienteRepository.save(cliente);
	}
	
	@PutMapping(value="/{id}")
	 public ResponseEntity<Cliente> update(@PathVariable("id") long id, @RequestBody Cliente cliente){
		if (id != cliente.getId()) {
			return new ResponseEntity<Cliente>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return clienteRepository.findById(id).map(record -> {
		 record.setId(cliente.getId());
		 record.setNombre(cliente.getNombre());
		 record.setApellidoUno(cliente.getApellidoUno());
		 record.setApellidoDos(cliente.getApellidoDos());
		 record.setLugarResidencia(cliente.getLugarResidencia());
		 record.setDireccionCobro(cliente.getDireccionCobro());
		 record.setNumTarjeta(cliente.getNumTarjeta());
		 record.setNumTarjeta(cliente.getNumTarjeta());
		 record.setMesVencimiento(cliente.getMesVencimiento());
		 Cliente updated = clienteRepository.save(record);
		 return ResponseEntity.ok().body(updated);
		 }).orElse(ResponseEntity.notFound().build());
	 }
	
	
	@DeleteMapping(path ={"/{id}"})
	 public ResponseEntity<?> delete(@PathVariable("id") long id) {
		boolean clienteTieneOrdenes = clienteTieneOrdenes(id);
		
		if(clienteTieneOrdenes == true) {
			return new ResponseEntity<Orden>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		 return clienteRepository.findById(id).map(record -> {
			 clienteRepository.deleteById(id);
			 return ResponseEntity.ok().build();
		 }).orElse(ResponseEntity.notFound().build());
	 }

	public boolean clienteTieneOrdenes(long id) {
		boolean clienteTieneOrdenes = false;
		
		List<Orden> ordenes = ordenRepository.findAll();
		
		for(Orden orden: ordenes) {
			if(orden.getCliente() == (int)id) {
				clienteTieneOrdenes = true;
				break;
			}
		}
		return clienteTieneOrdenes;
	}

	
	
	
}
