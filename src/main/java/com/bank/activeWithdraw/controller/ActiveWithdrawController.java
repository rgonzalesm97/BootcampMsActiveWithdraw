package com.bank.activeWithdraw.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.activeWithdraw.model.History;
import com.bank.activeWithdraw.service.ActiveWithdrawService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/active-withdraw")
public class ActiveWithdrawController {
	
	private final ActiveWithdrawService activeWithdrawService;

	@PostMapping("/consume/{id}")
	public Mono<History> withdraw(@PathVariable("id") String idProduct,
								@RequestParam Double amount) {
		
		return activeWithdrawService.consumeCredit(idProduct, amount);
		
	}
	
}
