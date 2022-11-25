package com.bolsadeideas.springboot.backend.discogs.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.bolsadeideas.springboot.backend.discogs.models.entity.Cliente;
import com.bolsadeideas.springboot.backend.discogs.models.entity.Cuenta;
import com.bolsadeideas.springboot.backend.discogs.models.entity.Movimiento;

@Repository
public interface IMovimientoDao extends PagingAndSortingRepository<Movimiento, Long> {
	
	@Query("select mov from Movimiento mov where mov.cuenta.id=?1 order by mov.fecha desc")
    public List<Movimiento> findByCuentaID(Long term);

}
