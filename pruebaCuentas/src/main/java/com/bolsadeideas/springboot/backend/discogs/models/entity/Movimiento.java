package com.bolsadeideas.springboot.backend.discogs.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	private Date fecha=new Date();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_tipo_movimiento")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private TipoMovimiento tipoMovimiento;
	

	private Double valor;
	
	private Double saldo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_cuenta")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JsonBackReference
    private Cuenta cuenta;
	
	private String clave=UUID.randomUUID().toString();

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
	
	

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
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

	@Override
	public String toString() {
		return "Movimiento [id=" + id + ", fecha=" + fecha + ", tipoMovimiento=" + tipoMovimiento + ", valor=" + valor
				+ ", saldo=" + saldo + ", cuenta=" + cuenta + ", clave=" + clave + "]";
	}

	/*@Override
	public String toString() {
		return "Movimiento [id=" + id + ", fecha=" + fecha + ", tipoMovimiento=" + tipoMovimiento + ", valor=" + valor
				+ ", saldo=" + saldo +", clave=" + clave + "]";
	}*/
	
	
	
	

}
