package com.bank.activeWithdraw.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.bank.activeWithdraw.model.History;
import com.bank.activeWithdraw.model.Credit;
import com.bank.activeWithdraw.proxy.ActiveWithdrawProxy;
import com.bank.activeWithdraw.service.ActiveWithdrawService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ActiveWithdrawServiceImpl implements ActiveWithdrawService{

	private final ActiveWithdrawProxy activeWithdrawProxy = new ActiveWithdrawProxy();
	
	@Override
	public Mono<History> consumeCredit(String idCredit, Double amount) {
		
		return activeWithdrawProxy.getCredit(idCredit)
				 				  .flatMap(resp->checkBalance(resp, amount))
				 				  .flatMap(resp->consumeCredit(resp, amount))
				 				  .flatMap(activeWithdrawProxy::updateCredit)
				 				  .flatMap(resp->saveHistory(idCredit, "credit consume", amount, null));
		
	}	
	
	
	//AVTIVEWITHDRAW UTIL METHODS
	public Mono<Credit> checkBalance(Credit credit, Double amount){
		return credit.getBalance() > amount ? Mono.just(credit)
											: Mono.error(() -> new IllegalArgumentException("Not enough balance"));
	}
	
	public Mono<Credit> consumeCredit(Credit credit, Double amount){
		credit.setBalance(credit.getBalance()-amount);
		credit.setDebt(credit.getDebt()+amount);
		return Mono.just(credit);
	}
	
	public Mono<History> saveHistory(String idProduct,
									String type,
									Double amount,
									String idThirdPartyProduct) {
		
		History history = new History();
		history.setIdProduct(idProduct);
		history.setType(type);
		history.setAmount(amount);
		history.setIdThirdPartyProduct(idThirdPartyProduct);
		history.setDate(new Date());
		
		return activeWithdrawProxy.saveHistory(history);
		
	}
	
}
