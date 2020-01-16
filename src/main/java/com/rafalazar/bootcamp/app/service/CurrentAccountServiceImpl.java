package com.rafalazar.bootcamp.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafalazar.bootcamp.app.documents.CurrentAccount;
import com.rafalazar.bootcamp.app.repository.CurrentAccountRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CurrentAccountServiceImpl implements CurrentAccountService{
	
	@Autowired
	CurrentAccountRepository repo;

	@Override
	public Flux<CurrentAccount> findAll() {
		return repo.findAll();
	}

	@Override
	public Mono<CurrentAccount> findById(String id) {
		return repo.findById(id);
	}

	@Override
	public Mono<CurrentAccount> save(CurrentAccount current) {
		return repo.save(current);
	}

	@Override
	public Mono<Void> delete(CurrentAccount current) {
		return repo.delete(current);
	}

}
