package com.example.demo.service;

import javax.inject.Named;

import com.example.demo.domain.Account;

@Named
public interface AccountService {

	Account getAccount(Long id);

	Account createAccount(Account accountDto);

	Account modifyAccount(Account account);
		
}
