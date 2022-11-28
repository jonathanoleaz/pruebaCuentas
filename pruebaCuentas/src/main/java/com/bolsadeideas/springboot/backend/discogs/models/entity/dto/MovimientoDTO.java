package com.bolsadeideas.springboot.backend.discogs.models.entity.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import com.bolsadeideas.springboot.backend.discogs.models.entity.Cuenta;
import com.bolsadeideas.springboot.backend.discogs.models.entity.Movimiento;
import com.bolsadeideas.springboot.backend.discogs.models.entity.TipoMovimiento;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



public class MovimientoDTO implements Serializable{

	 
	private static final long serialVersionUID = 1L;

	private Long id;
	private Date fecha=new Date();
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private TipoMovimiento tipoMovimiento;
	private Double valor;
	private Double saldo;
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Cuenta cuenta;
	private String clave;

    

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public TipoMovimiento getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
	
	

	@Override
	public String toString() {
		return "MovimientoDTO [id=" + id + ", fecha=" + fecha + ", tipoMovimiento=" + tipoMovimiento + ", valor="
				+ valor + ", saldo=" + saldo + ", cuenta=" + cuenta + ", clave=" + clave + "]";
	}



	//Builder class and methods
    public static class Builder{
    	private Long id;
    	private Date fecha;
        private TipoMovimiento tipoMovimiento;
    	private Double valor;
    	private Double saldo;
        private Cuenta cuenta;
    	private String clave;
      
    	public Builder id(Long id){
            this.id=id;
            return this;
        }
        public Builder fecha(Date fecha){
            this.fecha=fecha;
            return this;
        }
        public Builder tipoMovimiento(TipoMovimiento tipoMovimiento){
            this.tipoMovimiento=tipoMovimiento;
            return this;
        }
        public Builder valor(Double valor){
            this.valor=valor;
            return this;
        }
        public Builder saldo(Double saldo){
            this.saldo=saldo;
            return this;
        }
        public Builder cuenta(Cuenta cuenta){
            this.cuenta=cuenta;
            return this;
        }
        public Builder clave(String clave){
            this.clave=clave;
            return this;
        }

		public MovimientoDTO build(){
            return new MovimientoDTO(this);
        }
    }

    public MovimientoDTO(Builder builder) {
        this.id=builder.id;;
        this.fecha=builder.fecha;
        this.tipoMovimiento=builder.tipoMovimiento;
        this.valor=builder.valor;
        this.saldo=builder.saldo;
        this.cuenta=builder.cuenta;
        this.clave=builder.clave;
    }

    public MovimientoDTO(Long id, Date fecha, TipoMovimiento tipoMovimiento, Double valor, Double saldo, Cuenta cuenta, String clave) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.tipoMovimiento = tipoMovimiento;
		this.valor = valor;
		this.saldo = saldo;
		this.cuenta = cuenta;
		this.clave = clave;
	}

	public MovimientoDTO() {
    }

    public static MovimientoDTO convertMovimientoToDTO(Movimiento movimiento){
        Builder builder=new Builder();
        ArrayList<MovimientoDTO> movimientoDTOs=new ArrayList<>();

        MovimientoDTO movimDto=builder.id(movimiento.getId()).
        		fecha(movimiento.getFecha()).
        		tipoMovimiento(movimiento.getTipoMovimiento()).
        		valor(movimiento.getValor()).
        		saldo(movimiento.getSaldo()).
        		cuenta(movimiento.getCuenta()).
        		clave(movimiento.getClave()).
                build();

        
        return movimDto;
    }

    /*public static Artist convertDTOToArtist(MovimientoDTO artistDto){
        Artist artist=new Artist();
        ArrayList<Member> members=new ArrayList<>();
        for(MemberDTO memberDTO:artistDto.getMembers()) {
            members.add(MemberDTO.convertDTOToMember(memberDTO));
        }
        artist.setId(artistDto.getId());
        artist.setMembers(members);
        artist.setName(artistDto.getName());
        artist.setProfileDesc(artistDto.getProfileDesc());
        artist.setImageUrl(artistDto.getImageUrl());

        return artist;
    }*/
    
}