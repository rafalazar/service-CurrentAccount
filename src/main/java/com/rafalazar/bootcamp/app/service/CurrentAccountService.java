package com.rafalazar.bootcamp.app.service;

import com.rafalazar.bootcamp.app.documents.CurrentAccount;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CurrentAccountService {
	
	public Flux<CurrentAccount> findAll();
	
	public Mono<CurrentAccount> findById(String id);
	
	public Mono<CurrentAccount> save(CurrentAccount current);
	
	public Mono<Void> delete(CurrentAccount current);

}
