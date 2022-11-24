package com.bolsadeideas.springboot.backend.discogs.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "genero")
public class Genero implements Serializable{
	
	@Id
	@Column(name = "pk_genero")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name="nombre")
	@NotEmpty
	@Size(max=45) 
	private String nombre;
	
	/*@OneToMany(mappedBy = "genero", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Persona> personas;*/

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

	public Genero(Long id, @NotEmpty @Size(max = 45) String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public Genero() {
		super();
	}

	@Override
	public String toString() {
		return "Genero [id=" + id + ", nombre=" + nombre + "]";
	}
	
	

}
