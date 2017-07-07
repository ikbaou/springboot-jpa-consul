package com.example.demo.service;

import javax.inject.Inject;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Account;
import com.example.demo.entity.AccountEntity;
import com.example.demo.repository.AccountRepository;

@Transactional
public class AccountServiceImpl implements AccountService {
	
	private final Logger LOG = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	protected AccountRepository accountRepo;

	protected ModelMapper modelMapper;
	
	@Inject
	public AccountServiceImpl(ModelMapper modelMapper, AccountRepository accountRepo){
		super();
		this.modelMapper = modelMapper;
		this.accountRepo = accountRepo;
	}

	@Override
	public Account getAccount(Long id) {
			AccountEntity accountE = accountRepo.findOne(id);
			Account accountDto = accountE == null ? null : modelMapper.map(accountE, Account.class);
			LOG.debug("accountDto: {}", accountDto);
		return accountDto;
	}

}
