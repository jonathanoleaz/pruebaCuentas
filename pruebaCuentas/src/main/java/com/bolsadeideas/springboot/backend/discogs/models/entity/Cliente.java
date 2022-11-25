package com.bolsadeideas.springboot.backend.discogs.models.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cliente")
@PrimaryKeyJoinColumn(name = "fk_persona")
public class Cliente extends Persona implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "pk_cliente")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(name = "cliente_id")
	@Size(min=1, max=45) 
	private String clienteId;

	@NotEmpty
	private String contrasenia;
	
	//@NotEmpty
	@Column(nullable = false, columnDefinition = "TINYINT(1)")
	private boolean estado;	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClienteId() {
		return clienteId;
	}

	public void setClienteId(String clienteId) {
		this.clienteId = clienteId;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public boolean getEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Cliente(Long id, @NotEmpty @Size(min = 4, max = 45) String nombre, @NotEmpty Integer edad,
			@NotEmpty String identificacion, @NotEmpty String direccion, @NotEmpty String telefono, Long id2,
			@NotEmpty @Size(min = 4, max = 45) String clienteId, @NotEmpty String contrasenia,
			@NotEmpty Boolean estado) {
		super(id, nombre, edad, identificacion, direccion, telefono);
		id = id2;
		this.clienteId = clienteId;
		this.contrasenia = contrasenia;
		this.estado = estado;
	}

	public Cliente(Long id, @NotEmpty @Size(min = 4, max = 45) String nombre, @NotEmpty Integer edad,
			@NotEmpty String identificacion, @NotEmpty String direccion, @NotEmpty String telefono) {
		super(id, nombre, edad, identificacion, direccion, telefono);
	}
	
	public Cliente() {
		super();
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", clienteId=" + clienteId + ", contrasenia=" + contrasenia + ", estado=" + estado
				+ "]";
	}
	
	
	
}
