package com.lacomer.mx.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.lacomer.mx.dto.DireccionDto;

@Entity
public class Usuario {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nombre;
  private String apellidoPaterno;
  private String apellidoMaterno;
  private String correo;

  @OneToMany
  private DireccionDto direccion;

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}

public String getApellidoPaterno() {
	return apellidoPaterno;
}

public void setApellidoPaterno(String apellidoPaterno) {
	this.apellidoPaterno = apellidoPaterno;
}

public String getApellidoMaterno() {
	return apellidoMaterno;
}

public void setApellidoMaterno(String apellidoMaterno) {
	this.apellidoMaterno = apellidoMaterno;
}

public String getCorreo() {
	return correo;
}

public void setCorreo(String correo) {
	this.correo = correo;
}

public DireccionDto getDireccion() {
	return direccion;
}

public void setDireccion(DireccionDto direccion) {
	this.direccion = direccion;
}
  
  
}