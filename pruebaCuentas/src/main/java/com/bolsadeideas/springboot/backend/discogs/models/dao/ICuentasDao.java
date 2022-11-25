package com.bolsadeideas.springboot.backend.discogs.models.dao;



import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.bolsadeideas.springboot.backend.discogs.models.entity.Cliente;
import com.bolsadeideas.springboot.backend.discogs.models.entity.Cuenta;

@Repository
public interface ICuentasDao extends PagingAndSortingRepository<Cuenta, Long> {
	/*@Query("select cue from Cuenta cue where cue.fk_cliente=?1")
    public List<Cuenta> findByClienteId(Long idCliente);*/
   

}
