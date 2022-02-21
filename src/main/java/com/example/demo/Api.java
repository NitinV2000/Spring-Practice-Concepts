package com.example.demo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class Api {
	
	@Autowired
	private VendorRepo vendorRepo;
	@Autowired
	private ClientRepo clientRepo;
	
	@PostMapping("/upload")
	public ResponseEntity<String> uploadData(@RequestParam("file") MultipartFile file, @RequestParam("first") String f, @RequestParam("second") String s) throws Exception {
		if (file == null) {
			throw new RuntimeException("You must select the a file for uploading");
		}
		InputStream inputStream = file.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = br.readLine()) != null) {
        	String fnum = line.substring(0,line.indexOf(","));
            String snum = line.substring(line.indexOf(",")+1);
            if(fnum.equals(f) && snum.equals(s)) {
            	return new ResponseEntity<String>("Found", HttpStatus.OK);
            }
        }
		return new ResponseEntity<String>("Not Found", HttpStatus.OK);
	}
	
	@PostMapping(value = "/saveVendor")
	public Vendor saveVendor(@RequestBody Vendor vendor) {
		return vendorRepo.save(vendor);
	}
	
	@GetMapping(value = "/viewVendors")
	public List<Vendor> getVendors(){
		return vendorRepo.findAll();
	}
	@GetMapping(value = "/viewClients")
	public List<Client> getClients(){
		return clientRepo.findAll();
	}
	
	@GetMapping(value = "/viewVendor/{id}")
	public Vendor getVendor(@PathVariable("id") Long id) {
		return vendorRepo.findById(id).get();
	}
	
	@GetMapping(value = "/viewClient/{id}")
	public Client getClient(@PathVariable("id") Long id) {
		return clientRepo.findById(id).get();
	}
	
	@PostMapping(value = "/saveClient")
	public Client saveClient(@RequestBody Client client) {
		return clientRepo.save(client);
	}
	
	@PutMapping(value = "{vid}/add/{cid}")
	public Client addClientToVendor(@PathVariable("vid") Long vid, @PathVariable Long cid) {
		Vendor v = vendorRepo.findById(vid).get();
		Client c = clientRepo.findById(cid).get();
//		v.enrollClient(c);
		c.assignVendor(v);
		return clientRepo.save(c);
	}
	
	@DeleteMapping(value = "/del/{id}")
	public String delVendor(@PathVariable("id") Long id) {
		vendorRepo.deleteById(id);
		return "Deleted";
	}
}
