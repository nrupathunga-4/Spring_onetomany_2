package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Size;

@Entity
public class AadharCard {

	@Id
	@Size(min=5,max=12,message = "Must contain 12 digits only")
	private String adharnumber;

	@OneToOne(mappedBy = "aadharCard")
	private Person person;
	
	public String getAdharnumber() {
		return adharnumber;
	}

	public void setAdharnumber(String adharnumber) {
		this.adharnumber = adharnumber;
	}
	
	
}
