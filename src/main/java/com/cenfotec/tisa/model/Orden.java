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
public class Orden {
	
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 private String item;
	 private int cantidad;
	 private String imagen;
	 private int cliente;
	
	
	 public String getItem() {
			return item;
		}
		
	 public void setItem(String item) {
			this.item = item;
		}
	 
	 public int getCantidad() {
			return cantidad;
		}
		
	 public void setCantidad(int cantidad) {
			this.cantidad = cantidad;
		}
	 
	 public String getImagen() {
			return imagen;
		}
		
	 public void setImagen(String imagen) {
		 	this.imagen= imagen;
	 }
	 
	 public int getCliente() {
			return cliente;
		}
		
	 public void setCliente(int cliente) {
			this.cliente = cliente;
		}

}
