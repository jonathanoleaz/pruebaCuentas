package com.bolsadeideas.springboot.backend.discogs.models.entity.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.bolsadeideas.springboot.backend.discogs.models.entity.Cliente;
import com.bolsadeideas.springboot.backend.discogs.models.entity.Persona;

public class ClienteDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String clienteId;
	private String contrasenia;
	private boolean estado;
    
	private Persona persona;
    

    public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getClienteId() {
		return clienteId;
	}


	public void setClienteId(String clienteId) {
		this.clienteId = clienteId;
	}


	public String getContrasenia() {
		return contrasenia;
	}


	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}


	public boolean getEstado() {
		return estado;
	}


	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	


	public Persona getPersona() {
		return persona;
	}


	public void setPersona(Persona persona) {
		this.persona = persona;
	}


	public ClienteDTO(Builder builder) {
        this.id=builder.id;
        this.clienteId=builder.clienteId;
        this.contrasenia=builder.clienteId;
        this.estado=builder.estado;
        this.persona=builder.persona;
    }
    

    //Builder class and methods
    public static class Builder{
    	private Long id;
    	private String clienteId;
    	private String contrasenia;
    	private boolean estado;
    	private Persona persona;

        public Builder id(Long id){
            this.id=id;
            return this;
        }
        public Builder clienteId(String clienteId){
            this.clienteId=clienteId;
            return this;
        }
        public Builder contrasenia(String contrasenia){
            this.contrasenia=contrasenia;
            return this;
        }
        public Builder estado(boolean b){
            this.estado=b;
            return this;
        }
        public Builder persona(Persona persona){
            this.persona=persona;
            return this;
        }
        public ClienteDTO build(){
            return new ClienteDTO(this);
        }
    }


    public static ClienteDTO convertClienteToDTO(Cliente cliente){
        Builder builder=new Builder();

        ClienteDTO countryDTO=builder.id(cliente.getId()).
                            clienteId(cliente.getClienteId()).
                            contrasenia(cliente.getContrasenia()).
                            estado(cliente.getEstado()).
                            persona(new Persona(cliente.getId(), cliente.getNombre(), cliente.getEdad(), cliente.getIdentificacion(), cliente.getDireccion(), cliente.getTelefono())).
                            build();
        return countryDTO;
    }

    public static Cliente convertDTOToCountry(ClienteDTO countryDto){
        Cliente cliente = new Cliente(null, null, null, null, null, null);

        cliente.setId(countryDto.getId());
        cliente.setClienteId(countryDto.getClienteId());
        cliente.setContrasenia(countryDto.getContrasenia());
        cliente.setDireccion(countryDto.getPersona().getDireccion());
        cliente.setEdad(countryDto.getPersona().getEdad());
        cliente.setEstado(countryDto.getEstado());
        cliente.setGenero(countryDto.getPersona().getGenero());
        cliente.setIdentificacion(countryDto.getPersona().getIdentificacion());
        cliente.setNombre(countryDto.getPersona().getNombre());
        cliente.setTelefono(countryDto.getPersona().getTelefono());
        

        return cliente;
    }
}
