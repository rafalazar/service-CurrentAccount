package com.rafalazar.bootcamp.app.controllers;

import java.net.URI;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafalazar.bootcamp.app.documents.CurrentAccount;
import com.rafalazar.bootcamp.app.service.CurrentAccountService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/current_accounts")
public class CurrentAccountController {
	
	@Autowired
	private CurrentAccountService service;
	
	@GetMapping
	public Mono<ResponseEntity<Flux<CurrentAccount>>> list(){
		return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(service.findAll()));
	}
	
	@GetMapping("/{id}")
	public Mono<ResponseEntity<CurrentAccount>> searchById(@PathVariable String id){
		return service.findById(id).map(p -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(p))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Mono<ResponseEntity<CurrentAccount>> create(@RequestBody CurrentAccount current){
		if(current.getCreateAt() == null) {
			current.setCreateAt(new Date());
		}
		
		if(current.getUpdateAt() == null) {
			current.setUpdateAt(new Date());
		}
		
		return service.save(current).map(p -> ResponseEntity
				.created(URI.create("/api/current_accounts/".concat(p.getId())))
				.contentType(MediaType.APPLICATION_JSON)
				.body(p)
				);
	}
	
	@PutMapping("/{id}")
	public Mono<ResponseEntity<CurrentAccount>> edit(@RequestBody CurrentAccount current, @PathVariable String id){
		return service.findById(id).flatMap(ca -> {
			ca.setNumAccount(current.getNumAccount());
			ca.setNameBank(current.getNameBank());
			ca.setTotal(current.getTotal());
			ca.setCreateAt(current.getCreateAt());
			ca.setUpdateAt(current.getUpdateAt());
			ca.setStatus(current.getStatus());
			return service.save(ca);
		}).map(ca -> ResponseEntity.created(URI.create("/api/current_accounts/".concat(ca.getId())))
				.contentType(MediaType.APPLICATION_JSON).body(ca)).defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> delete(@PathVariable String id){
		return service.findById(id).flatMap(ca -> {
			return service.delete(ca).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
		}).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
	}

}
