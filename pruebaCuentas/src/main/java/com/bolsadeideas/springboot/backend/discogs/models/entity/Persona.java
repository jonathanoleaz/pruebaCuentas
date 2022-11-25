package com.bolsadeideas.springboot.backend.discogs.models.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "persona")
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "pk_persona")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Size(min=1, max=45)
	@Column(unique = true)
	private String nombre;

	//@NotEmpty
	private Integer edad;
	
	@NotEmpty
	private String identificacion;
	
	@NotEmpty
	private String direccion;
	
	@NotEmpty
	private String telefono;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_genero")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Genero genero;

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

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Persona(Long id, @NotEmpty @Size(min = 4, max = 45) String nombre, @NotEmpty Integer edad,
			@NotEmpty String identificacion, @NotEmpty String direccion, @NotEmpty String telefono) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.edad = edad;
		this.identificacion = identificacion;
		this.direccion = direccion;
		this.telefono = telefono;
	}

	public Persona() {
		super();
	}

	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", edad=" + edad + ", identificacion=" + identificacion
				+ ", direccion=" + direccion + ", telefono=" + telefono + "]";
	}
	
	

}
