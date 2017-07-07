package com.example.demo.config;

import javax.inject.Inject;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.AccountService;
import com.example.demo.service.AccountServiceImpl;
import com.example.demo.service.CustomerService;
import com.example.demo.service.CustomerServiceImpl;

@Configuration
public class BeanConfig{
	
	@Inject
	ModelMapper modelMapper;		
	
	@Inject
	AccountRepository accountRepo;	
	
	@Inject
	CustomerRepository customerRepo;		
	
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}		

	@Bean
	public CustomerService customerService() {
	    return new CustomerServiceImpl(modelMapper, customerRepo);
	}	
	
	@Bean
	public AccountService accountService() {
	    return new AccountServiceImpl(modelMapper, accountRepo);
	}	

}