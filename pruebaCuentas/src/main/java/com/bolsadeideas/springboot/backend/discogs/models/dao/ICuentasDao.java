package com.bolsadeideas.springboot.backend.discogs.models.dao;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bolsadeideas.springboot.backend.discogs.models.entity.Cuenta;

@Repository
public interface ICuentasDao extends CrudRepository<Cuenta, Long> {

   

}
