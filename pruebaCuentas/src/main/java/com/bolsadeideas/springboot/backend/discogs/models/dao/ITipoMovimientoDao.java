package com.bolsadeideas.springboot.backend.discogs.models.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bolsadeideas.springboot.backend.discogs.models.entity.TipoMovimiento;

@Repository
public interface ITipoMovimientoDao extends CrudRepository<TipoMovimiento, Long>{

}
