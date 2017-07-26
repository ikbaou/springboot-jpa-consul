package com.example.demo.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Customer;
import com.example.demo.entity.mapper.CustomerMapper;
import com.example.demo.repository.CustomerRepository;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
	
	CustomerRepository customerRepo;

	CustomerMapper customerMapper;
	
	
	@Inject
	public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepo){
		super();
		this.customerMapper = customerMapper;
		this.customerRepo = customerRepo;
	}	

	@Override
	public Customer getCustomer(Long id) {
		return customerMapper.toDomain(customerRepo.findOne(id));
	}
	
	@Override
	public Customer createCustomer(Customer customerDto){
		return customerMapper.toDomain( customerRepo.saveAndFlush(customerMapper.toEntity(customerDto)) );
	}	

}
