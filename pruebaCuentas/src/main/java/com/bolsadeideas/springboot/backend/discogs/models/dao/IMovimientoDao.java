package com.bolsadeideas.springboot.backend.discogs.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

import com.bolsadeideas.springboot.backend.discogs.models.entity.Movimiento;

@Repository
public interface IMovimientoDao extends PagingAndSortingRepository<Movimiento, Long> {
	
	@Query("select mov from Movimiento mov where mov.cuenta.id=?1 order by mov.fecha desc")
    public List<Movimiento> findByCuentaID(Long term);
	
	//@Query(value= "select cli from Cliente cli, Cuenta cue, Movimiento tmo where cue.id=tmo.cuenta.id and cli.id=?1 and tmo.fecha")
		@Query(value="select * from persona per, cliente cli, cuenta cue, movimiento mov "
				+ "where cli.pk_cliente=cue.fk_cliente "
				+ "and cue.pk_cuenta=mov.fk_cuenta "
				+ "and per.pk_persona=cli.fk_persona "
				+ "and cli.pk_cliente = ?1 "
				+ "and mov.fecha >= ?2 and mov.fecha <= ?3 ;", nativeQuery = true)
	    public List<Movimiento> findByIdAndDates(Long clienteId, Date fechaInicio, Date fechaFin);

}
