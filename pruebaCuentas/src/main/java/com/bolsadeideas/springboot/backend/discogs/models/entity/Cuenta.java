package com.bolsadeideas.springboot.backend.discogs.models.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cuenta")
public class Cuenta implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "pk_cuenta")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(name = "numero_cuenta")
	@Size(min=4, max=45) 
	private String numeroCuenta;
	

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_tipo_cuenta")
    private TipoCuenta tipoCuenta;

    //@NotEmpty
	@Column(name = "saldo_inicial")
	private Double saldoInicial;
    
    //@NotEmpty
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
	private boolean estado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public TipoCuenta getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(TipoCuenta tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public Double getSaldoInicial() {
		return saldoInicial;
	}

	public void setSaldoInicial(Double saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	public boolean getEstado() {
		return estado;
	}

	public void setEstado(@NotEmpty boolean estado) {
		this.estado = estado;
	}

	public Cuenta(Long id, @NotEmpty @Size(min = 4, max = 45) String numeroCuenta, TipoCuenta tipoCuenta,
			@NotEmpty Double saldoInicial, @NotEmpty boolean estado) {
		super();
		this.id = id;
		this.numeroCuenta = numeroCuenta;
		this.tipoCuenta = tipoCuenta;
		this.saldoInicial = saldoInicial;
		this.estado = estado;
	}

	public Cuenta() {
		super();
	}
}
