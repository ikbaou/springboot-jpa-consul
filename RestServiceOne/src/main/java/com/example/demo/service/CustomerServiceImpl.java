package com.example.demo.service;

import javax.inject.Inject;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Customer;
import com.example.demo.entity.CustomerEntity;
import com.example.demo.repository.CustomerRepository;

@Transactional
public class CustomerServiceImpl implements CustomerService {
	
	private final Logger LOG = LoggerFactory.getLogger(CustomerServiceImpl.class);

	CustomerRepository customerRepo;

	ModelMapper modelMapper;
	
	@Inject
	public CustomerServiceImpl(ModelMapper modelMapper, CustomerRepository customerRepo){
		super();
		this.modelMapper = modelMapper;
		this.customerRepo = customerRepo;
	}	

	@Override
	public Customer getCustomer(Long id) {
		CustomerEntity customerE = customerRepo.findOne(id);
		Customer customerDto = customerE == null ? null : modelMapper.map(customerE, Customer.class);
		LOG.debug("accountDto: {}", customerDto);
		return customerDto;
	}

}
