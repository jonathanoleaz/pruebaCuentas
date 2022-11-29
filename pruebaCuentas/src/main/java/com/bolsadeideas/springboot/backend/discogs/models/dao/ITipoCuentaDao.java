package com.bolsadeideas.springboot.backend.discogs.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bolsadeideas.springboot.backend.discogs.models.entity.TipoCuenta;
import com.bolsadeideas.springboot.backend.discogs.models.entity.TipoMovimiento;

@Repository
public interface ITipoCuentaDao extends CrudRepository<TipoMovimiento, Long>{
	
	@Query("select cue from TipoCuenta cue where cue.nombre=?1")
    public List<TipoCuenta> findByNombre(String term);

}
