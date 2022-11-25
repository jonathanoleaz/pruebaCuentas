package com.bolsadeideas.springboot.backend.discogs.models.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.backend.discogs.models.dao.IClienteDao;
import com.bolsadeideas.springboot.backend.discogs.models.dao.ICuentasDao;
import com.bolsadeideas.springboot.backend.discogs.models.dao.IGeneroDao;
import com.bolsadeideas.springboot.backend.discogs.models.dao.ITipoCuentaDao;
import com.bolsadeideas.springboot.backend.discogs.models.entity.Cliente;
import com.bolsadeideas.springboot.backend.discogs.models.entity.Cuenta;
import com.bolsadeideas.springboot.backend.discogs.models.entity.Genero;
import com.bolsadeideas.springboot.backend.discogs.models.entity.Persona;
import com.bolsadeideas.springboot.backend.discogs.models.entity.TipoCuenta;

@Service
public class CuentaServiceImpl implements ICuentaService{

	@Autowired
    ICuentasDao cuentaDao;
    
    @Autowired
    ITipoCuentaDao tipoCuentaDao;
    
    @Autowired
    IClienteDao clienteDao;

    @Override
    @Transactional(readOnly = true)
    public Page<Cuenta> findAll(PageRequest pageRequest){
        Page<Cuenta> cuentasPage=cuentaDao.findAll(pageRequest);
        /*ArrayList<AlbumDTO> listAlbumsDTO=new ArrayList<>();
        for(Album album : albumsPage.getContent()){
            listAlbumsDTO.add(AlbumDTO.convertAlbumToDTO(album));
        }*/

        PageImpl<Cuenta> cuentasDtoPage=new PageImpl<Cuenta>(cuentasPage.getContent(), pageRequest, cuentasPage.getTotalElements());
        return (cuentasDtoPage) ;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cuenta> findOne(Long id) {
        //return AlbumDTO.convertAlbumToDTO(albumDao.findOne(id));
    	return cuentaDao.findById(id);
    }

    @Override
    @Transactional
    public Cuenta save(Cuenta cuenta) {
    	TipoCuenta tipoCuenta = new TipoCuenta();
    	Cuenta cu = new Cuenta();
    	Persona per = new Persona();
    	Cliente cli = new Cliente();
    	
    	//Obtencion del tipo de cuenta por su nombre
    	List<TipoCuenta> tiposCuenta = tipoCuentaDao.findByNombre(cuenta.getTipoCuenta().getNombre());
    	tipoCuenta = tiposCuenta.get(0);
    	
    	//Obtencion del cliente y persona por su nombre
    	List<Cliente> clientes = clienteDao.findByNombre(cuenta.getCliente().getNombre());
    	cli = clientes.get(0);
    	
    	if(tiposCuenta.size()==0) {
    		throw new ValidationException("No se encontro genero.");
    	}
    	
    	cuenta.setTipoCuenta(tipoCuenta);
    	cuenta.setCliente(cli);
        cu = cuentaDao.save(cuenta);

        return cu;
    }

    @Override
    @Transactional
    public void delete(Long id) {
    	Cuenta c = cuentaDao.findById(id).get();
    	//List<Cliente> = cuentaDao.findByClienteId(c.getCliente().getId());
        cuentaDao.delete(c);
    }

}
