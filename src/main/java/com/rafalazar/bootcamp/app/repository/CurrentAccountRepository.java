package com.rafalazar.bootcamp.app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.rafalazar.bootcamp.app.documents.CurrentAccount;

public interface CurrentAccountRepository extends ReactiveMongoRepository<CurrentAccount, String>{

}
