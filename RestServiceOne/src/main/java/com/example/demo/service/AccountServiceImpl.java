package com.example.demo.service;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Account;
import com.example.demo.entity.mapper.AccountMapper;
import com.example.demo.repository.AccountRepository;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
	
	private final Logger LOG = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	protected CustomerService customerService;
	
	protected AccountRepository accountRepo;

	@Inject
	protected AccountMapper accountMapper;
	
	@Inject
	public AccountServiceImpl(
			AccountMapper accountMapper,
			AccountRepository accountRepo,
			CustomerService customerService){
		super();
		this.accountMapper = accountMapper;
		this.accountRepo = accountRepo;
		this.customerService = customerService;
	}

	@Override
	public Account getAccount(Long id) {
		return accountMapper.toDomain(accountRepo.findOne(id));
	}
	
	@Override
	public Account createAccount(Account accountDto){
		//create customer
		accountDto.setCustomer(customerService.createCustomer(accountDto.getCustomer()));
		//create account
		accountDto = accountMapper.toDomain(accountRepo.saveAndFlush(accountMapper.toEntity(accountDto)));
		LOG.debug("accountDto: {}", accountDto);
		return accountDto;
	}

	
	@Override
	public Account modifyAccount(Account accountDto){		
		accountDto = accountMapper.toDomain(accountRepo.saveAndFlush(accountMapper.toEntity(accountDto)));
		LOG.debug("accountDto: {}", accountDto);
		return accountDto;
	}	
}
