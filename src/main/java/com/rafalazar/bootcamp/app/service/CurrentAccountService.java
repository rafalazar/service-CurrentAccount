package com.rafalazar.bootcamp.app.service;

import com.rafalazar.bootcamp.app.documents.CurrentAccount;
import com.rafalazar.bootcamp.app.dto.AccountDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CurrentAccountService {
	
	public Flux<CurrentAccount> findAll();
	
	public Mono<CurrentAccount> findById(String id);
	
	public Mono<CurrentAccount> save(CurrentAccount current);
	
	public Mono<Void> delete(CurrentAccount current);
	
	//------------->
//	public Mono<CurrentAccount> savePersonal(AccountDto accountDto);
//	public Mono<CurrentAccount> saveEnterprise(AccountDto accountDto);
	
	//------------->
//	
//	public Mono<CurrentAccount> findByNumAccount(String numAccount);
//	
//	public Flux<CurrentAccount> findByDni(String dni);

}
