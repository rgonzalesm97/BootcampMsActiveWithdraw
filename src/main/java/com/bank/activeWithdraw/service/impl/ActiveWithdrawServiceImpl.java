package com.bank.activeWithdraw.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.bank.activeWithdraw.model.History;
import com.bank.activeWithdraw.model.Credit;
import com.bank.activeWithdraw.proxy.ActiveWithdrawProxy;
import com.bank.activeWithdraw.service.ActiveWithdrawService;

import reactor.core.publisher.Mono;

@Service
public class ActiveWithdrawServiceImpl implements ActiveWithdrawService{

	private ActiveWithdrawProxy activeWithdrawProxy = new ActiveWithdrawProxy();
	
	@Override
	public Mono<Credit> ConsumeCredit(String idCredit, Double amount) {
		
		Mono<Credit> actualCredit = activeWithdrawProxy.getCredit(idCredit);
		
		return actualCredit.flatMap(x -> {
			if(x.getBalance()>=amount) {
				x.setBalance(x.getBalance()-amount);
				x.setDebt(x.getDebt()+amount);
				
				return activeWithdrawProxy.updateAccount(x)
											.doOnSuccess(y -> {
												if(y.getId()!=null) {
													saveHistory(y.getId(), "credit consume", amount);
												}
											});
			}else {
				return Mono.empty();
			}
		});
	}	
	
	public void saveHistory(String idProduct,
							String type,
							Double amount) {
		History history = new History();
		history.setIdProduct(idProduct);
		history.setType(type);
		history.setAmount(amount);
		history.setDate(new Date());
		
		activeWithdrawProxy.saveHistory(history);
		
	}
	
}
