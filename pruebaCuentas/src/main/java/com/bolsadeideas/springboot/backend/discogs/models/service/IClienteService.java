package com.bolsadeideas.springboot.backend.discogs.models.service;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.bolsadeideas.springboot.backend.discogs.models.entity.Cliente;

public interface IClienteService {
    public Page<Cliente> findAll(PageRequest pageRequest);
    public Cliente findOne(Long id);
    public Cliente save(Cliente cliente);
    public void delete(Long id);
}
