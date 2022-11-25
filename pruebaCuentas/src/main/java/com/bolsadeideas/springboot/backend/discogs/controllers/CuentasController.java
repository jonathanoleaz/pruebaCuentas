package com.bolsadeideas.springboot.backend.discogs.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.bolsadeideas.springboot.backend.discogs.models.entity.Cliente;
import com.bolsadeideas.springboot.backend.discogs.models.entity.Cuenta;
import com.bolsadeideas.springboot.backend.discogs.models.service.IClienteService;
import com.bolsadeideas.springboot.backend.discogs.models.service.ICuentaService;

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
@RequestMapping("/cuentas")
public class CuentasController {
    @Autowired
	private ICuentaService cuentaService;

    @GetMapping("/")
	public Page<Cuenta> index(
			@RequestParam(name = "page", defaultValue = "0") int page, 
			@RequestParam(name = "pageSize", defaultValue = "10") int pageSize
		)
	{
		PageRequest pageRequest = PageRequest.of(page, pageSize);

		Page<Cuenta> countriesDto = cuentaService.findAll(pageRequest);
		
		return countriesDto;
	}


	@GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> show(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		
		Cuenta cuenta = null;
		try {
			cuenta = cuentaService.findOne(id).orElse(null);
		} catch (DataAccessException e) {
			response.put("message", "Query error");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().toString()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(cuenta == null) {
			response.put("message", "La cuenta con ID: ".concat(id.toString().concat(" no se encuentra registrado.")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Cuenta>(cuenta, HttpStatus.OK);
	}


	@PostMapping("/")
	public ResponseEntity<?> create(@Valid @RequestBody Cuenta cuenta, BindingResult result) {
		
		Cuenta newCuenta =null;
		Map<String, Object> response = new HashMap<>();
		
		if(cuenta.getId()!=null) {
			List<String> errors = new ArrayList<>();
			errors.add("El campo ID se utiliza para actualizar");
			response.put("err", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		/*Validacion de campos especificada en javaxValidation*/
		if(result.hasErrors()) {
			List<String> errors = new ArrayList<>();
			for(FieldError err: result.getFieldErrors()) {
				errors.add("El campo: "+err.getField() +" "+ err.getDefaultMessage());
			}
			response.put("err", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			newCuenta = cuentaService.save(cuenta);
		}catch (DataAccessException e) {
			response.put("message", "Error registrando en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().toString()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("message", "Cuenta creada");
		response.put("cuenta", cuenta);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/")
	public ResponseEntity<?> update(@Valid @RequestBody Cuenta cuenta, BindingResult result) {
		
		Cuenta newCuenta =null;
		Map<String, Object> response = new HashMap<>();
		
		if(cuenta.getId()==null) {
			List<String> errors = new ArrayList<>();
			errors.add("El campo ID: es requerido");
			response.put("err", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		/*Validacion de campos especificada en javaxValidation*/
		if(result.hasErrors()) {
			List<String> errors = new ArrayList<>();
			for(FieldError err: result.getFieldErrors()) {
				errors.add("El campo: "+err.getField() +" "+ err.getDefaultMessage());
			}
			response.put("err", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			newCuenta = cuentaService.save(cuenta);
		}catch (DataAccessException e) {
			response.put("message", "Error registrando en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().toString()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("message", "Cuenta actualizada");
		response.put("cuenta", cuenta);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		
		Cuenta cuenta = null;
		try {
			cuenta = cuentaService.findOne(id).orElse(null);
		} catch (DataAccessException e) {
			response.put("message", "Query error");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().toString()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(cuenta == null) {
			response.put("message", "El cliente con ID: ".concat(id.toString().concat(" no se encuentra registrado.")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}else {
			cuentaService.delete(id);
		}
		response.put("message", "Cliente eliminado");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
	}	
}
