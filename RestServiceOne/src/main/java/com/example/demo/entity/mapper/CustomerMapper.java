package com.example.demo.entity.mapper;

import javax.enterprise.inject.Default;
import javax.inject.Named;

import com.example.demo.domain.Customer;
import com.example.demo.entity.CustomerEntity;
import com.example.demo.util.AbstractMapper;
import com.example.demo.util.ClassUtils;

@Default
@Named
public class CustomerMapper extends AbstractMapper<Customer, CustomerEntity> {

	@Override
	protected CustomerEntity prepareEntity(CustomerEntity entity, Customer domain) {
		ClassUtils.setIfNotNull(entity::setId, domain::getId);
		ClassUtils.setIfNotNull(entity::setFirstname, domain::getFirstname);
		ClassUtils.setIfNotNull(entity::setLastname, domain::getLastname);
		return entity;
	}

	@Override
	protected Customer prepareDomain(Customer domain, CustomerEntity entity) {
		ClassUtils.setIfNotNull(domain::setId, entity::getId);
		ClassUtils.setIfNotNull(domain::setFirstname, entity::getFirstname);
		ClassUtils.setIfNotNull(domain::setLastname, entity::getLastname);
		return domain;
	}

}
