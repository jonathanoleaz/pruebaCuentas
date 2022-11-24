package com.bolsadeideas.springboot.backend.discogs.models.entity.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


import com.bolsadeideas.springboot.backend.discogs.models.entity.TipoCuenta;

public class CuentaDTO implements Serializable{
    /**
	 * 
	
	private Long id;
	private String numeroCuenta;
    private TipoCuenta tipoCuenta;
	private Double saldoInicial;
	private Integer estado;

    public CuentaDTO(Builder builder) {
        this.id=builder.id;
        this.title=builder.title;
        this.launchDate=builder.launchDate;
        this.notes=builder.notes;
        this.imageUrl=builder.imageUrl;
        this.artists=builder.artists;
        this.styles=builder.styles;
    }

    public static Album convertDTOToAlbum(CuentaDTO albumDTO){
        Album album=new Album();
        ArrayList<Artist> artists=new ArrayList<>();
        for(MovimientoDTO artistDTO : albumDTO.getArtists()){
            artists.add(MovimientoDTO.convertDTOToArtist(artistDTO));
        }

        ArrayList<Style> styles=new ArrayList<>();
        for(StyleDTO styleDTO : albumDTO.getStyles()){
            styles.add(StyleDTO.convertDtoToStyle(styleDTO));
        }

        album.setId(albumDTO.getId());
        album.setLaunchDate(albumDTO.getLaunchDate());
        album.setNotes(albumDTO.getNotes());
        album.setTitle(albumDTO.getTitle());
        album.setImageUrl(albumDTO.getImageUrl());
        album.setArtists(artists);
        album.setStyles(styles);

        return album;
    }

    public static CuentaDTO convertAlbumToDTO(Album album){
        Builder builder = new Builder();
        ArrayList<MovimientoDTO> artistsDtos=new ArrayList<>();
        for(Artist artist : album.getArtists()){
            artistsDtos.add(MovimientoDTO.convertArtistToDTO(artist));            
        }

        ArrayList<StyleDTO> styleDTOs=new ArrayList<>();
        for(Style style : album.getStyles()){
            styleDTOs.add(StyleDTO.convertStyleToDTO(style));
        }

        CuentaDTO albumDTO=builder.id(album.getId()).
                        title(album.getTitle()).
                        launchDate(album.getLaunchDate()).
                        notes(album.getNotes()).
                        imageUrl(album.getImageUrl()).
                        artists(artistsDtos).
                        styles(styleDTOs).
                        build();

        return albumDTO;
    }

    public Integer getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public Date getLaunchDate() {
        return launchDate;
    }
    public String getNotes() {
        return notes;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public List<MovimientoDTO> getArtists() {
        return artists;
    }
    public List<StyleDTO> getStyles() {
        return styles;
    }

    public static class Builder{
    	private Long id;
    	private String numeroCuenta;
        private TipoCuenta tipoCuenta;
    	private Double saldoInicial;
    	private Integer estado;

        public Builder id(Integer id){
            this.id=id;
            return this;
        }
        public Builder title(String title){
            this.title=title;
            return this;
        }
        public Builder launchDate(Date launchDate){
            this.launchDate=launchDate;
            return this;
        }
        public Builder notes(String notes){
            this.notes=notes;
            return this;
        }
        public Builder imageUrl(String imageUrl){
            this.imageUrl=imageUrl;
            return this;
        }
        public Builder artists(List<MovimientoDTO> artists){
            this.artists=artists;
            return this;
        }
        public Builder styles(List<StyleDTO> styles){
            this.styles=styles;
            return this;
        }
        public CuentaDTO build(){
            return new CuentaDTO(this);
        }
    }
     */
}