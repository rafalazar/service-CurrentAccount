package com.rafalazar.bootcamp.app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.rafalazar.bootcamp.app.documents.CurrentAccount;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CurrentAccountRepository extends ReactiveMongoRepository<CurrentAccount, String>{
	//Buscamos por dni y nombre de banco
	public Flux<CurrentAccount> findByDniBankName(String dni, String bankName);
	
	//Buscamos por número de cuenta.
	public Mono<CurrentAccount> findByNumberAccount(String numAccount);
	
	//Buscamos por dni
	public Flux<CurrentAccount> findByDni(String dni);
}
