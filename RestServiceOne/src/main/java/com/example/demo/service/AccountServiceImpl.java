package com.example.demo.service;

import javax.inject.Inject;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Account;
import com.example.demo.domain.Customer;
import com.example.demo.entity.AccountEntity;
import com.example.demo.repository.AccountRepository;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
	
	private final Logger LOG = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	protected CustomerService customerService;
	
	protected AccountRepository accountRepo;

	protected ModelMapper modelMapper;
	
	@Inject
	public AccountServiceImpl(
			ModelMapper modelMapper, 
			AccountRepository accountRepo,
			CustomerService customerService){
		super();
		this.modelMapper = modelMapper;
		this.accountRepo = accountRepo;
		this.customerService = customerService;
	}

	@Override
	public Account getAccount(Long id) {
			AccountEntity accountE = accountRepo.findOne(id);
			Account accountDto = accountE == null ? null : modelMapper.map(accountE, Account.class);
			LOG.debug("accountDto: {}", accountDto);
		return accountDto;
	}
	
	@Override
	public Account createAccount(Account accountDto){
		Customer customer = customerService.createCustomer(accountDto.getCustomer());
		accountDto.setCustomer(customer);
		
		AccountEntity accountE = modelMapper.map(accountDto, AccountEntity.class);
		accountE = accountRepo.saveAndFlush(accountE);
		accountDto = accountE == null ? null : modelMapper.map(accountE, Account.class);
		LOG.debug("accountDto: {}", accountDto);
		return accountDto;
	}

	
	@Override
	public Account modifyAccount(Account accountDto){
		
		AccountEntity accountE = modelMapper.map(accountDto, AccountEntity.class);
		accountE = accountRepo.saveAndFlush(accountE);
		accountDto = accountE == null ? null : modelMapper.map(accountE, Account.class);
		LOG.debug("accountDto: {}", accountDto);
		return accountDto;
	}	
}
