package com.bank.activeWithdraw.service;

import com.bank.activeWithdraw.model.Credit;

import reactor.core.publisher.Mono;

public interface ActiveWithdrawService {
	public Mono<Credit> ConsumeCredit(String idCredit, Double amount);
}