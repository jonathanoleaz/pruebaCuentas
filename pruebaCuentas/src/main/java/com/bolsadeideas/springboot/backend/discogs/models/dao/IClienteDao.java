package com.bolsadeideas.springboot.backend.discogs.models.dao;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.bolsadeideas.springboot.backend.discogs.models.entity.Cliente;
import com.bolsadeideas.springboot.backend.discogs.models.entity.TipoCuenta;

@Repository
public interface IClienteDao extends PagingAndSortingRepository<Cliente, Long>{
	@Query("select cli from Cliente cli where cli.nombre=?1")
    public List<Cliente> findByNombre(String term);
	
	//@Query(value= "select cli from Cliente cli, Cuenta cue, Movimiento tmo where cue.id=tmo.cuenta.id and cli.id=?1 and tmo.fecha")
	@Query(value="select * from persona per, cliente cli, cuenta cue, movimiento mov, tipo_movimiento tmo, tipo_cuenta tcu, genero gen "
			+ "where cli.pk_cliente=cue.fk_cliente "
			+ "and cue.pk_cuenta=mov.fk_cuenta "
			+ "and mov.fk_tipo_movimiento=tmo.pk_tipo_movimiento "
			+ "and per.pk_persona=cli.fk_persona "
			+ "and cue.fk_tipo_cuenta=tcu.pk_tipo_cuenta "
			+ "and gen.pk_genero=per.fk_genero "
			+ "and cli.pk_cliente = ?1 "
			+ "and mov.fecha >= ?2 and mov.fecha <= ?3 ;", nativeQuery = true)
    public List<Cliente> findByIdAndDates(Long clienteId, Date fechaInicio, Date fechaFin);
	
}
