package com.bank.activeWithdraw.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;

import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.bank.activeWithdraw.model.Credit;
import com.bank.activeWithdraw.model.History;
import com.bank.activeWithdraw.proxy.ActiveWithdrawProxy;
import com.bank.activeWithdraw.service.impl.ActiveWithdrawServiceImpl;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class ActiveWithdrawServiceImplTest {
	
	@Mock
	private static ActiveWithdrawProxy proxy;
	
	private static ActiveWithdrawServiceImpl service;
	
	@BeforeAll
	public static void setUp() {
		proxy = mock(ActiveWithdrawProxy.class);
//		service = new ActiveWithdrawServiceImpl(proxy);
	}
	
	@Test
	void consumeCredit() {
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
		
		Mockito.when(proxy.getCredit(credit.getId()).thenReturn(Mono.just(credit)));
		Mockito.when(proxy.updateCredit(credit).thenReturn(Mono.just(credit)));
		Mockito.when(proxy.saveHistory(any())).thenReturn(Mono.just(history));
		
		Mono<History> result = service.consumeCredit(credit.getId(), Double.valueOf(1500));
		
		StepVerifier.create(result)
					.expectNext(history)
					.verifyComplete();
	}
}
