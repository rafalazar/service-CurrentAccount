package com.rafalazar.bootcamp.app;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.rafalazar.bootcamp.app.documents.CurrentAccount;
import com.rafalazar.bootcamp.app.service.CurrentAccountService;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class ServiceCurrentAccountApplication implements CommandLineRunner{

	@Autowired
	private CurrentAccountService service;
	
	@Autowired
	private ReactiveMongoTemplate mongoTemplate;

	private static final Logger log = LoggerFactory.getLogger(ServiceCurrentAccountApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ServiceCurrentAccountApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		mongoTemplate.dropCollection("current_account").subscribe();
		
		Flux.just(
				new CurrentAccount("4345354","BCP",1800.90,"activo"),
				new CurrentAccount("6553435","Interbank",650.65,"inactivo"),
				new CurrentAccount("5189768","BBVA",420.10,"activo")
			).flatMap(ca ->{
				ca.setCreateAt(new Date());
				ca.setUpdateAt(new Date());
				return service.save(ca);
			})
		.subscribe(ca -> log.info("Insert: " + ca.getNumAccount() + " -> " + ca.getNameBank()))
		;
		
	}

}
