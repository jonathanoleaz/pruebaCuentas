package com.bolsadeideas.springboot.backend.discogs.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//import com.bolsadeideas.springboot.app.models.entity.Factura;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "movimiento")
public class Movimiento implements Serializable{
	
	@Id
	@Column(name = "pk_movimiento")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "fecha", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	@NotEmpty
	private Date fecha;
	
	@ManyToOne(fetch = FetchType.LAZY)
    private TipoMovimiento tipoMovimiento;
	
	@NotEmpty
	private Double valor;
	
	@NotEmpty
	private Double saldo;
	
	@NotEmpty
	private Double clave;

}
