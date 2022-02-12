package com.bank.activeWithdraw.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.bank.activeWithdraw.service.ActiveWithdrawService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Service
public class ActiveWithdrawServiceImpl implements ActiveWithdrawService{
	
	private WebClient productClient = WebClient.builder().baseUrl("http://localhost:8081/product").build();
	
	
}
