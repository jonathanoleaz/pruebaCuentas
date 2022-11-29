package com.bolsadeideas.springboot.backend.discogs.models.service;

import com.bolsadeideas.springboot.backend.discogs.models.dao.IClienteDao;
import com.bolsadeideas.springboot.backend.discogs.models.dao.IGeneroDao;
import com.bolsadeideas.springboot.backend.discogs.models.entity.Cliente;
import com.bolsadeideas.springboot.backend.discogs.models.entity.Genero;

import java.util.List;
import java.util.Optional;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteServiceImpl implements IClienteService{
    @Autowired
    IClienteDao clienteDao;
    
    @Autowired
    IGeneroDao generoDao;

    @Override
    @Transactional(readOnly = true)
    public Page<Cliente> findAll(PageRequest pageRequest){
        Page<Cliente> albumsPage=clienteDao.findAll(pageRequest);
        /*ArrayList<AlbumDTO> listAlbumsDTO=new ArrayList<>();
        for(Album album : albumsPage.getContent()){
            listAlbumsDTO.add(AlbumDTO.convertAlbumToDTO(album));
        }*/

        PageImpl<Cliente> albumsDtoPage=new PageImpl<Cliente>(albumsPage.getContent(), pageRequest, albumsPage.getTotalElements());
        return (albumsDtoPage) ;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cliente> findOne(Long id) {
        //return AlbumDTO.convertAlbumToDTO(albumDao.findOne(id));
    	return clienteDao.findById(id);
    }

    @Override
    @Transactional
    public Cliente save(Cliente cliente) {
    	Genero genero = new Genero();
    	Cliente cl = new Cliente();
    	List<Genero> generos= generoDao.findByNombre(cliente.getGenero().getNombre());
    	if(generos.size()==0) {
    		throw new ValidationException("No se encontro genero.");
    	}
    	genero = generos.get(0);
    	cliente.setGenero(genero);
        cl = clienteDao.save(cliente);

        return cl;
    }

    @Override
    @Transactional
    public void delete(Long id) {
    	Cliente c = clienteDao.findById(id).get();
        clienteDao.delete(c);
    }
    
    
}