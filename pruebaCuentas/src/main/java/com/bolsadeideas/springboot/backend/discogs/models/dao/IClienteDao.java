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
	
	
	
}
