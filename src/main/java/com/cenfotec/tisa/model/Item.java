package com.cenfotec.tisa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Item {
	
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	
	 private String nombre;
	 private int costo;
	 private String descripcion;
	 
	 public String getNombre() {
			return nombre;
		}
		
	 public void setNombre(String nombre) {
			this.nombre = nombre;
		}
	 
	 public int getCosto() {
			return costo;
		}
		
	 public void setCosto(int costo) {
			this.costo = costo;
		}
	 
	 public String getDescripcion() {
			return descripcion;
		}
		
	 public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}
	 

}
