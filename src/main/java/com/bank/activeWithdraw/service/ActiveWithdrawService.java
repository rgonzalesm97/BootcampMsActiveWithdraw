package com.bank.activeWithdraw.service;

import com.bank.activeWithdraw.model.History;

import reactor.core.publisher.Mono;

public interface ActiveWithdrawService {
	public Mono<History> ConsumeCredit(String idCredit, Double amount);
}