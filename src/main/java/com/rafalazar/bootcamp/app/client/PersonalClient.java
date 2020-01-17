package com.rafalazar.bootcamp.app.client;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.rafalazar.bootcamp.app.dto.PersonalDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonalClient {
	
	private static final Logger log = LoggerFactory.getLogger(PersonalClient.class);
	
	@Autowired
	private WebClient client;
	
	//Traemos a todos los clientes --------------------
	public Flux<PersonalDto> selectAll(){ 
		return client.get().accept(MediaType.APPLICATION_JSON)
				.exchange()
				.flatMapMany(response -> response.bodyToFlux(PersonalDto.class));
	}
	
	//Buscamos por el id del clientePersonal
	public Mono<PersonalDto> findById(String id){
		return client.get().uri("/{id}",Collections.singletonMap("id", id))
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(PersonalDto.class);
	}
	
	//Guardamos un cliente
	public Mono<PersonalDto> save(PersonalDto personal){
		log.info("Enviando datos del cliente...");
		
		return client.post().accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromValue(personal))
				.retrieve()
				.bodyToMono(PersonalDto.class);
	}
	
	//Eliminamos un cliente
	public Mono<Void> delete(String id){
		return client.delete().uri("/{id}", Collections.singletonMap("id", id))
				.exchange()
				.then();
	}
	
	//Actualizamos un cliente
	public Mono<PersonalDto> update(PersonalDto personal, String id){
		log.info("Actualizando datos...");
		
		return client.put().uri("/{id}",Collections.singletonMap("id", id))
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(personal)
				.retrieve()
				.bodyToMono(PersonalDto.class);
	}
	
	//Buscar por numero de dni
	public Mono<PersonalDto> findByDni(String dni){
		return client.get()
				.uri("/legal/{dni}",Collections.singletonMap("dni", dni))
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(PersonalDto.class);
	}
	

}
