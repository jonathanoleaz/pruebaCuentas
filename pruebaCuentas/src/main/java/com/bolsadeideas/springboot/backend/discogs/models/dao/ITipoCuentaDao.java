package com.bolsadeideas.springboot.backend.discogs.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.bolsadeideas.springboot.backend.discogs.models.entity.Movimiento;
import com.bolsadeideas.springboot.backend.discogs.models.entity.TipoCuenta;
import com.bolsadeideas.springboot.backend.discogs.models.entity.TipoMovimiento;

public interface ITipoCuentaDao extends CrudRepository<TipoMovimiento, Long>{

}
