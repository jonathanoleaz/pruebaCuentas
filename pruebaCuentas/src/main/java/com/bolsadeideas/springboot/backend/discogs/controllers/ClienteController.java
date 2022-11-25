package com.bolsadeideas.springboot.backend.discogs.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.validation.Valid;

import com.bolsadeideas.springboot.backend.discogs.models.entity.Cliente;
import com.bolsadeideas.springboot.backend.discogs.models.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin /**enabled only for development environment String nameSpanish, String nameEnglish*/
@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
	private IClienteService clienteService;

    @GetMapping("/")
	public Page<Cliente> index(
			@RequestParam(name = "page", defaultValue = "0") int page, 
			@RequestParam(name = "pageSize", defaultValue = "10") int pageSize
		)
	{
		PageRequest pageRequest = PageRequest.of(page, pageSize);

		Page<Cliente> countriesDto = clienteService.findAll(pageRequest);
		
		return countriesDto;
	}


	@GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> show(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		
		Cliente cliente = null;
		try {
			cliente = clienteService.findOne(id).orElse(null);
		} catch (DataAccessException e) {
			response.put("message", "Query error");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().toString()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(cliente == null) {
			response.put("message", "El cliente con ID: ".concat(id.toString().concat(" no se encuentra registrado.")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}


	@PostMapping("/")
	public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, BindingResult result) {
		
		Cliente newCliente =null;
		Map<String, Object> response = new HashMap<>();
		
		if(cliente.getId()!=null) {
			List<String> errors = new ArrayList<>();
			errors.add("El campo ID se utiliza para actualizar");
			response.put("err", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		/*Validacion de campos especificada en javaxValidation*/
		if(result.hasErrors()) {
			List<String> errors = new ArrayList<>();
			for(FieldError err: result.getFieldErrors()) {
				System.out.println(err.getField());
				errors.add("El campo: "+err.getField() +" "+ err.getDefaultMessage());
			}
			response.put("err", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			newCliente = clienteService.save(cliente);
		}catch (DataAccessException e) {
			response.put("message", "Error registrando en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().toString()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("message", "Cliente creado");
		response.put("cliente", cliente);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/")
	public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente, BindingResult result) {
		
		Cliente newCliente =null;
		Map<String, Object> response = new HashMap<>();
		
		if(cliente.getId()==null) {
			List<String> errors = new ArrayList<>();
			errors.add("El campo ID: es requerido");
			response.put("err", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		/*Validacion de campos especificada en javaxValidation*/
		if(result.hasErrors()) {
			List<String> errors = new ArrayList<>();
			for(FieldError err: result.getFieldErrors()) {
				System.out.println(err.getField());
				errors.add("El campo: "+err.getField() +" "+ err.getDefaultMessage());
			}
			response.put("err", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			newCliente = clienteService.save(cliente);
		}catch (DataAccessException e) {
			response.put("message", "Error registrando en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().toString()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("message", "Cliente actualizado");
		response.put("cliente", cliente);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		
		Cliente cliente = null;
		try {
			cliente = clienteService.findOne(id).orElse(null);
		} catch (DataAccessException e) {
			response.put("message", "Query error");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().toString()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(cliente == null) {
			response.put("message", "El cliente con ID: ".concat(id.toString().concat(" no se encuentra registrado.")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}else {
			clienteService.delete(id);
		}
		response.put("message", "Cliente eliminado");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(value="/estadoCuenta", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getEstadoDeCuenta(@RequestParam Map<String, String> params) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
		
		Map<String, Object> response = new HashMap<>();
		
		String idCliente=params.get("clienteid");
		String fechaInicio=params.get("fechainicio");
		String fechaFin=params.get("fechafin");
		
		
		if(idCliente==null || fechaInicio==null || fechaFin==null) {
			response.put("message", "Parametros incorrectos");
			response.put("error", "Parametros incorrectos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		List<Cliente> cliente = null;
		
		Date fechaI=null;
		Date fechaF=null;
		try {
			fechaI = formatter.parse(fechaInicio);
			fechaF = formatter.parse(fechaFin);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 
		Long idCli = Long.parseLong(idCliente);
		
		try {
			cliente = clienteService.findByIdAndDates(idCli, fechaI, fechaF);
		} catch (DataAccessException e) {
			response.put("message", "Query error");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().toString()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		/*if(cliente == null) {
			response.put("message", "El cliente con ID: ".concat(id.toString().concat(" no se encuentra registrado.")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}*/
		return new ResponseEntity<List<Cliente>>(cliente, HttpStatus.OK);
	}
}
