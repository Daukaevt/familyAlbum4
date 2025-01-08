package com.wixsite.mupbam1.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="pictures")
public class Pics {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private long id;
	private String description;
	private String url;
	private String owner_key;
	
	public Pics(String description, String url, String owner_key) {
		super();
		this.description = description;
		this.url = url;
	}

}
