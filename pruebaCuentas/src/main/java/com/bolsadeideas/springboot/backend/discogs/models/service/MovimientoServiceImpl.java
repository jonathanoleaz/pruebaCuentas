package com.bolsadeideas.springboot.backend.discogs.models.service;

import java.util.Date;
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
import com.bolsadeideas.springboot.backend.discogs.models.dao.IMovimientoDao;
import com.bolsadeideas.springboot.backend.discogs.models.dao.ITipoCuentaDao;
import com.bolsadeideas.springboot.backend.discogs.models.dao.ITipoMovimientoDao;
import com.bolsadeideas.springboot.backend.discogs.models.entity.Cliente;
import com.bolsadeideas.springboot.backend.discogs.models.entity.Cuenta;
import com.bolsadeideas.springboot.backend.discogs.models.entity.Movimiento;
import com.bolsadeideas.springboot.backend.discogs.models.entity.Persona;
import com.bolsadeideas.springboot.backend.discogs.models.entity.TipoMovimiento;

@Service
public class MovimientoServiceImpl implements IMovimientoService{

	@Autowired
    ICuentasDao cuentaDao;
    
    @Autowired
    ITipoCuentaDao tipoCuentaDao;
    
    @Autowired
    IClienteDao clienteDao;
    
    @Autowired
    IMovimientoDao movimientoDao;
    
    @Autowired
    ITipoMovimientoDao tipoMovimientoDao;

    @Override
    @Transactional(readOnly = true)
    public Page<Movimiento> findAll(PageRequest pageRequest){
        Page<Movimiento> movimientosPage=movimientoDao.findAll(pageRequest);
        /*ArrayList<AlbumDTO> listAlbumsDTO=new ArrayList<>();
        for(Album album : albumsPage.getContent()){
            listAlbumsDTO.add(AlbumDTO.convertAlbumToDTO(album));
        }*/

        PageImpl<Movimiento> cuentasDtoPage=new PageImpl<Movimiento>(movimientosPage.getContent(), pageRequest, movimientosPage.getTotalElements());
        return (cuentasDtoPage) ;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Movimiento> findOne(Long id) {
        //return AlbumDTO.convertAlbumToDTO(albumDao.findOne(id));
    	return movimientoDao.findById(id);
    }

    @Override
    @Transactional
    public Movimiento save(Movimiento movimiento) {
    	Cuenta cuenta = new Cuenta();
    	Persona per = new Persona();
    	Cliente cli = new Cliente();
    	TipoMovimiento tipMov = new TipoMovimiento();
    	Movimiento movim = new Movimiento();
    	
    	//Obtencion del tipo de cuenta por su nombre
    	List<Cuenta> cuentas = cuentaDao.findByNumeroCuenta(movimiento.getCuenta().getNumeroCuenta());
    	if(cuentas.size()==0) {
    		throw new ValidationException("No se encontro la cuenta.");
    	}
    	cuenta = cuentas.get(0);
    	
    	movimiento.setCuenta(cuenta);
    	//Obtencion del cliente y persona por su nombre
    	List<TipoMovimiento> tipoMovimientos = tipoMovimientoDao.findByNombre(movimiento.getTipoMovimiento().getNombre());
    	
    	if(tipoMovimientos.size()==0) {
    		throw new ValidationException("No se encontro el tipo de movimiento.");
    	}
    	tipMov = tipoMovimientos.get(0);
    	movimiento.setTipoMovimiento(tipMov);
    	
    	/*Obtenemos los movimientos de la misma cuenta del movimiento*/
    	List<Movimiento> movimientosCuenta = movimientoDao.findByCuentaID(cuenta.getId());
    	
    	if(tipMov.getNombre().equals("DEBITO")) {
    		/*si no hay movimientos previos en la cuenta, utilizar como referencia el saldo inicial de la cuenta*/
    		if(movimientosCuenta.size()==0) {
    			if(cuenta.getSaldoInicial() - movimiento.getValor() < 0) {
    				throw new ValidationException("Saldo insuficiente.");
    			}
    		}else {
    			/*si ya hay movimientos previos en la cuenta, utilizar como referencia el saldo del ultimo movimiento (ya vienen ordenados por fecha)*/
    			if(movimientosCuenta.get(0).getSaldo() - movimiento.getValor() < 0) {
    				throw new ValidationException("Saldo insuficiente.");
    			}
    		}
    		
    		movimiento.setValor(movimiento.getValor()*(-1));
    	}
    	/*Calculo del nuevo saldo que se guarda en movimiento.saldo, considerar que ya se modifico el signo si es debito, por lo que con la suma es suficiente*/
    	if(movimientosCuenta.size()==0) {
    		movimiento.setSaldo(cuenta.getSaldoInicial() + movimiento.getValor());
		}else {
			/*si ya hay movimientos previos en la cuenta, utilizar como referencia el saldo del ultimo movimiento (ya vienen ordenados por fecha)*/
			movimiento.setSaldo(movimientosCuenta.get(0).getSaldo() + movimiento.getValor());
		}
    	
    	
    	
    	
        movim = movimientoDao.save(movimiento);

        return movim;
    }

    @Override
    @Transactional
    public void delete(Long id) {
    	Cuenta c = cuentaDao.findById(id).get();
    	//List<Cliente> = cuentaDao.findByClienteId(c.getCliente().getId());
        cuentaDao.delete(c);
    }
    
    
    @Override
    @Transactional
    public List<Movimiento> findByIdAndDates(Long clienteId, Date fechaInicio, Date fechaFin) {

    	List<Movimiento> movimientos= movimientoDao.findByIdAndDates(clienteId, fechaInicio, fechaFin);

        return movimientos;
    }

}
