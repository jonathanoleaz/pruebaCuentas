package com.bolsadeideas.springboot.backend.discogs.models.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.bolsadeideas.springboot.backend.discogs.models.entity.Cliente;
import com.bolsadeideas.springboot.backend.discogs.models.entity.Cuenta;
import com.bolsadeideas.springboot.backend.discogs.models.entity.Movimiento;

public interface ICuentaService {
	public Page<Cuenta> findAll(PageRequest pageRequest);
    public Optional<Cuenta> findOne(Long id);
    public Cuenta save(Cuenta cliente);
    public void delete(Long id);
    

}
