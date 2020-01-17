package com.rafalazar.bootcamp.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafalazar.bootcamp.app.client.EnterpriseClient;
import com.rafalazar.bootcamp.app.client.PersonalClient;
import com.rafalazar.bootcamp.app.documents.CurrentAccount;
import com.rafalazar.bootcamp.app.dto.AccountDto;
import com.rafalazar.bootcamp.app.repository.CurrentAccountRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CurrentAccountServiceImpl implements CurrentAccountService{
	
	private static final Logger log = LoggerFactory.getLogger(CurrentAccountServiceImpl.class);
	
	@Autowired
	CurrentAccountRepository repo;
	
	@Autowired
	private PersonalClient pclient;
	
	@Autowired
	private EnterpriseClient eclient;

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

//	@Override
//	public Mono<CurrentAccount> findByNumAccount(String numAccount) {
//		return repo.findByNumberAccount(numAccount);
//	}
//
//	@Override
//	public Flux<CurrentAccount> findByDni(String dni) {
//		return repo.findByDni(dni);
//	}

	//Guardamos el cliente personal
//	@Override
//	public Mono<CurrentAccount> savePersonal(AccountDto accountDto) {
//		
//		return pclient.findByDni(accountDto.getNumberAccount())
//				.flatMap(personal -> {
//					return repo.findByDniBankName(accountDto.getNumberAccount(), accountDto.getTypeAccount()).count()
//							.flatMap(ac -> {
//								log.info("cantidad de cuentas x Num/Tipo: "+ac);
//								if(ac == 0)
//									return repo.save(convert.con)
//							})
//				});
//	}

	//Guardamos el cliente empresarial
//	@Override
//	public Mono<CurrentAccount> saveEnterprise(AccountDto accountDto) {
//		return null;
//	}
//
//	@Override
//	public Mono<CurrentAccount> savePersonal(AccountDto accountDto) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
