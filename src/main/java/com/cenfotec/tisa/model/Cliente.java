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
public class Cliente {

	@Id
	 private long id;
	
	 private String nombre;
	 private String apellidoUno;
	 private String apellidoDos;
	 private String lugarResidencia;
	 private String direccionCobro;
	 private String numTarjeta;
	 private int mesVencimiento;
	 private int anioVencimiento;
	 
	 public long getId() {
		 return id;
	 }
	 
	 public void setId(long id) {
		 this.id = id;
	 }
	
	 public String getNombre() {
			return nombre;
		}
		
	 public void setNombre(String nombre) {
			this.nombre = nombre;
		}
	 
	 public String getApellidoUno() {
			return apellidoUno;
		}
		
	 public void setApellidoUno(String apellidoUno) {
			this.apellidoUno = apellidoUno;
		}
	 
	 public String getApellidoDos() {
			return apellidoDos;
		}
		
	 public void setApellidoDos(String apellidoDos) {
			this.apellidoDos = apellidoDos;
		}
	 
	 public String getLugarResidencia() {
			return lugarResidencia;
		}
		
	 public void setLugarResidencia(String lugarResidencia) {
			this.lugarResidencia = lugarResidencia;
		}
	 
	 public String getDireccionCobro() {
			return direccionCobro;
		}
		
	 public void setDireccionCobro(String direccionCobro) {
			this.direccionCobro = direccionCobro;
		}
	 
	 public String getNumTarjeta() {
			return numTarjeta;
		}
		
	 public void setNumTarjeta(String numTarjeta) {
			this.numTarjeta = numTarjeta;
		}
	
	 public int getMesVencimiento() {
			return mesVencimiento;
		}
		
	 public void setMesVencimiento(int mesVencimiento) {
			this.mesVencimiento = mesVencimiento;
		}
	 
	 public int getAnioVencimiento() {
			return anioVencimiento;
		}
		
	 public void setAnioVencimiento(int anioVencimiento) {
			this.anioVencimiento = anioVencimiento;
		}
		

}
