package com.bolsadeideas.springboot.backend.discogs.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin /**enabled only for development environment String nameSpanish, String nameEnglish*/
@RestController
@RequestMapping("/cliente")
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
			cliente = clienteService.findOne(id);
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
		
		System.out.println(cliente.toString());
		
		/*Validacion de campos especificada en javaxValidation*/
		if(result.hasErrors()) {
			System.out.println(result.toString());
			System.out.println("sds");
			/*Forma anterior, sin expresion lambda*/
			List<String> errors = new ArrayList<>();
			for(FieldError err: result.getFieldErrors()) {
				System.out.println(err.getField());
				errors.add("El campo: "+err.getField() +" "+ err.getDefaultMessage());
			}
			/*List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> 
					{
						return "The field: "+err.getField() +" "+ err.getDefaultMessage();
					}
					).collect(Collectors.toList());*/
			response.put("err", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			newCliente = clienteService.save(cliente);
		}catch (DataAccessException e) {
			response.put("message", "Error inserting in the DB");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().toString()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("message", "The country was created successfully");
		response.put("country", cliente);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
}
