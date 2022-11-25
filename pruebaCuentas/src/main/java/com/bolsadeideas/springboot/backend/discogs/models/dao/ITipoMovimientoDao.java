package com.bolsadeideas.springboot.backend.discogs.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bolsadeideas.springboot.backend.discogs.models.entity.Genero;
import com.bolsadeideas.springboot.backend.discogs.models.entity.TipoMovimiento;

@Repository
public interface ITipoMovimientoDao extends CrudRepository<TipoMovimiento, Long>{
	@Query("select tmo from TipoMovimiento tmo where tmo.nombre=?1")
    public List<TipoMovimiento> findByNombre(String term);
}
