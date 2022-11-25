package com.bolsadeideas.springboot.backend.discogs.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bolsadeideas.springboot.backend.discogs.models.entity.Cuenta;
import com.bolsadeideas.springboot.backend.discogs.models.entity.Genero;

@Repository
public interface IGeneroDao extends CrudRepository<Genero, Long> {
	
	@Query("select gen from Genero gen where gen.nombre=?1")
    public List<Genero> findByNombre(String term);

}
