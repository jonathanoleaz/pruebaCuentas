package com.bolsadeideas.springboot.backend.discogs.models.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.bolsadeideas.springboot.backend.discogs.models.entity.Cliente;
import com.bolsadeideas.springboot.backend.discogs.models.entity.Cuenta;
import com.bolsadeideas.springboot.backend.discogs.models.entity.Movimiento;

public interface IMovimientoService {
	public Page<Movimiento> findAll(PageRequest pageRequest);
    public Optional<Movimiento> findOne(Long id);
    public Movimiento save(Movimiento movimiento);
    public void delete(Long id);

}
