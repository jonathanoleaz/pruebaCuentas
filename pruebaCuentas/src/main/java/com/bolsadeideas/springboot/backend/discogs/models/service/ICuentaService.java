package com.bolsadeideas.springboot.backend.discogs.models.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.bolsadeideas.springboot.backend.discogs.models.entity.Cliente;
import com.bolsadeideas.springboot.backend.discogs.models.entity.Cuenta;

public interface ICuentaService {
	public Page<Cuenta> findAll(PageRequest pageRequest);
    public Cuenta findOne(Long id);
    public Cuenta save(Cuenta cliente);
    public void delete(Long id);

}
