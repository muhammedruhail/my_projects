package com.ruhail.mechinetestapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.*;



/**
 * A Media
 */
@Entity
@Table(name = "media")
public class Media {
	
	@Id
	private String id;

    @Column(name = "url")
	private String url;// No need to store it, but i did just for testing
    
    @Lob
    private byte[] image;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	
	

}
