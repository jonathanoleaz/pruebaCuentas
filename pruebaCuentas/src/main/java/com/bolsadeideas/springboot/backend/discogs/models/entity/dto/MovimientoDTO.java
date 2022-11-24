package com.bolsadeideas.springboot.backend.discogs.models.entity.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public class MovimientoDTO implements Serializable{
    /**
	 * 
	 
	private static final long serialVersionUID = 1L;
	private Integer id;
    private String name;
    private String profileDesc;
    private String imageUrl;
    private List<MemberDTO> members;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getProfileDesc() {
        return profileDesc;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public List<MemberDTO> getMembers() {
        return members;
    }

    //Builder class and methods
    public static class Builder{
        private Integer id;
        private String name;
        private String profileDesc;
        private String imageUrl;
        private List<MemberDTO> members;

        public Builder id(Integer id){
            this.id=id;
            return this;
        }
        public Builder name(String name){
            this.name=name;
            return this;
        }
        public Builder profileDesc(String profileDesc){
            this.profileDesc=profileDesc;
            return this;
        }
        public Builder imageUrl(String imageUrl){
            this.imageUrl=imageUrl;
            return this;
        }
        public Builder members(List<MemberDTO> members){
            this.members=members;
            return this;
        }
        public MovimientoDTO build(){
            return new MovimientoDTO(this);
        }
    }

    public MovimientoDTO(Builder builder) {
        this.id=builder.id;
        this.name=builder.name;
        this.profileDesc=builder.profileDesc;
        this.members=builder.members;
        this.imageUrl=builder.imageUrl;
    }

    public MovimientoDTO(Integer id, String name, String profileDesc, String imageUrl, List<MemberDTO> members) {
        this.id = id;
        this.name = name;
        this.profileDesc = profileDesc;
        this.members = members;
        this.imageUrl = imageUrl;
    }
    

    public MovimientoDTO() {
    }

    public static MovimientoDTO convertArtistToDTO(Artist artist){
        Builder builder=new Builder();
        ArrayList<MemberDTO> membersDTO=new ArrayList<>();

        for (Member member : artist.getMembers()) {
            membersDTO.add(MemberDTO.convertMemberToDTO(member));
        }

        MovimientoDTO artistDTO=builder.id(artist.getId()).
                            name(artist.getName()).
                            profileDesc(artist.getProfileDesc()).
                            imageUrl(artist.getImageUrl()).
                            members(membersDTO).
                            build();
        return artistDTO;
    }

    public static Artist convertDTOToArtist(MovimientoDTO artistDto){
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
    }
    */
}