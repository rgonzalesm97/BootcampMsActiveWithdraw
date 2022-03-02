package com.bank.activeWithdraw.controller;

import static org.mockito.Mockito.mock;

import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.bank.activeWithdraw.model.Credit;
import com.bank.activeWithdraw.model.History;
import com.bank.activeWithdraw.service.ActiveWithdrawService;

import reactor.core.publisher.Mono;

public class ActiveWithdrawControllerTest {
	
	private static WebTestClient webTestClient;
	
	@Mock
	private static ActiveWithdrawService service;
	
	@BeforeAll
	public static void setUp() {
		service = mock(ActiveWithdrawService.class);
		webTestClient = WebTestClient.bindToController(new ActiveWithdrawController(service))
									 .configureClient()
									 .baseUrl("/active-withdraw")
									 .build();
	}
	
	@Test
	void withdraw() {
		Credit credit = new Credit();
		credit.setId("23jk4hid23hf");
		credit.setIdClient("23jk4hidClient23hf");
		credit.setCardNumber("23jk4hidCard23hf");
		credit.setTypeCredit("23jk4htypeAccount23hf");
		credit.setAccountNumber("23jk4hcreditNumber23hf");
		credit.setBalance(Double.valueOf(28500.0));
		credit.setCredit(Double.valueOf(35000.0));
		credit.setDebt(Double.valueOf(1500));
		
		History history = new History();
		history.setIdProduct("23jk4hid23hf");
		history.setType("credit consume");
		history.setAmount(Double.valueOf(1500));
		history.setIdThirdPartyProduct(null);
		history.setDate(new Date());
		
		Mockito.when(service.consumeCredit(credit.getId(), Double.valueOf(1500))).thenReturn(Mono.just(history));
		
		webTestClient.post().uri("/consume/{id}?amount={amount}", credit.getId(), Double.valueOf(1500))
							.exchange()
							.expectStatus().isOk();
	}
}
