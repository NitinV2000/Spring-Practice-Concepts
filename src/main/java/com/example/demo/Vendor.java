package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Vendor {
	
	@Id     
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    
    @OneToMany(mappedBy = "vendor", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Client> clients = new ArrayList<>();
    
//    public void addClient(Client client){
//        clients.add(client);
//        client.setVendor(this);
//    }
//    public void removeBook(Client client){
//    	clients.remove(client);
//    	client.setVendor(null);
//    }

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

	public Vendor() {
		super();
	}

	public Vendor(Long id, String name, List<Client> clients) {
		super();
		this.id = id;
		this.name = name;
		this.clients = clients;
	}

	public List<Client> getClients() {
		return clients;
	}

	@Override
	public String toString() {
		return "Vendor [id=" + id + ", name=" + name + ", clients=" + clients + "]";
	}
	public void enrollClient(Client c) {
		// TODO Auto-generated method stub
		clients.add(c);
	}
}
