package com.bank.activeWithdraw.proxy;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.bank.activeWithdraw.model.History;
import com.bank.activeWithdraw.model.Credit;

import reactor.core.publisher.Mono;

public class ActiveWithdrawProxy {
	
	private final WebClient.Builder webClientBuilder = WebClient.builder();
	
	public Mono<Credit> getCredit(String idProduct){
		return webClientBuilder.build()
								.get()
								.uri("http://localhost:8090/credit/" + idProduct)
								.retrieve()
								.bodyToMono(Credit.class);
	}
	
	public Mono<Credit> updateCredit(Credit credit){
		return webClientBuilder.build()
								.put()
								.uri("http://localhost:8090/credit/")
								.contentType(MediaType.APPLICATION_JSON)
								.body(BodyInserters.fromValue(credit))
								.retrieve()
								.bodyToMono(Credit.class);
	}
	
	public Mono<History> saveHistory(History history) {
		return webClientBuilder.build()
						.post()
						.uri("http://localhost:8090/history")
						.contentType(MediaType.APPLICATION_JSON)
						.body(BodyInserters.fromValue(history))
						.retrieve()
						.bodyToMono(History.class);
	}
}
