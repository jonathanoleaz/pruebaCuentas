package com.bolsadeideas.springboot.backend.discogs.models.service;

import com.bolsadeideas.springboot.backend.discogs.models.dao.IClienteDao;
import com.bolsadeideas.springboot.backend.discogs.models.entity.Cliente;
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
    public Cliente findOne(Long id) {
        //return AlbumDTO.convertAlbumToDTO(albumDao.findOne(id));
    	return clienteDao.findById(id).get();
    }

    @Override
    @Transactional
    public Cliente save(Cliente cliente) {
        Cliente c = clienteDao.save(cliente);

        return c;
    }

    @Override
    @Transactional
    public void delete(Long id) {
    	Cliente c = clienteDao.findById(id).get();
        clienteDao.delete(c);
    }    
}