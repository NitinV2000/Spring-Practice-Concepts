package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Client {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	private String name;
	
	@JsonIgnore
    @ManyToOne
	@JoinColumn(name="vendor_id", referencedColumnName = "id")
    private Vendor vendor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public Client(Long id, String name, Vendor vendor) {
		super();
		this.id = id;
		this.name = name;
		this.vendor = vendor;
	}

	public Client() {
		super();
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", vendor=" + vendor + "]";
	}

	public void assignVendor(Vendor v) {
		// TODO Auto-generated method stub
		this.vendor = v;
	}
    
    
}
