package com.bolsadeideas.springboot.backend.discogs.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

import com.bolsadeideas.springboot.backend.discogs.models.entity.Movimiento;

public interface IMovimientoDao extends CrudRepository<Movimiento, Long> {


}
