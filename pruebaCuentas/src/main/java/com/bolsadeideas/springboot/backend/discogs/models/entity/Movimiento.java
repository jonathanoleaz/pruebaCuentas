package com.bolsadeideas.springboot.backend.discogs.models.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "movimiento")
public class Movimiento implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "pk_movimiento")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "fecha", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	@NotEmpty
	private Date fecha;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_tipo_movimiento")
    private TipoMovimiento tipoMovimiento;
	

	private Double valor;
	

	private Double saldo;
	
	@NotEmpty
	private String clave;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public TipoMovimiento getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public String getClave() {
		return clave;
	}

	public void setClave( String clave) {
		this.clave = clave;
	}

	public Movimiento(Long id, @NotEmpty Date fecha, TipoMovimiento tipoMovimiento, @NotEmpty Double valor,
			@NotEmpty Double saldo, String clave) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.tipoMovimiento = tipoMovimiento;
		this.valor = valor;
		this.saldo = saldo;
		this.clave = clave;
	}

	public Movimiento() {
		super();
	}
	
	

}
