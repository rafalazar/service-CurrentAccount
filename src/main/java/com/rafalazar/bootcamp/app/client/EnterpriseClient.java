package com.rafalazar.bootcamp.app.client;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.rafalazar.bootcamp.app.dto.EnterpriseDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EnterpriseClient {
	private static final Logger log = LoggerFactory.getLogger(EnterpriseClient.class);

	@Autowired
	@Qualifier("enterprise")
	private WebClient eclient;

	// Listamos todos los clientes Empresariales
	public Flux<EnterpriseDto> findAll() {

		return eclient.get().accept(MediaType.APPLICATION_JSON).exchange()
				.flatMapMany(response -> response.bodyToFlux(EnterpriseDto.class));
	}

	// Buscamos por el id del cliente empresarial
	public Mono<EnterpriseDto> findById(String id) {

		log.info("Searching for: " + id);

		return eclient.get()
				.uri("/{id}", Collections.singletonMap("id", id))
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(EnterpriseDto.class);
	}

	// Guardamo un cliente
	public Mono<EnterpriseDto> save(EnterpriseDto enterpriseDto) {

		log.info("Saving Enterprise Client...");

		return eclient.post().accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromValue(enterpriseDto))
				.retrieve()
				.bodyToMono(EnterpriseDto.class);
	}

	// Eliminamos un cliente empresarial
	public Mono<Void> delete(String id) {
		return eclient.delete()
				.uri("/{id}", Collections.singletonMap("id", id))
				.exchange()
				.then();
	}

	// Actualizamos un cliente ya registrado
	public Mono<EnterpriseDto> update(EnterpriseDto enterpriseDto, String id) {

		log.info("Actualizando cliente empresarial...");

		return eclient.put().uri("/{id}", Collections.singletonMap("id", id))
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(enterpriseDto).retrieve()
				.bodyToMono(EnterpriseDto.class);
	}

	// Buscamos por RUC
	public Mono<EnterpriseDto> findByNumDoc(String ruc) {
		return eclient.get()
				.uri("/ruc/{ruc}", Collections.singletonMap("ruc", ruc))
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(EnterpriseDto.class);
	}

}
