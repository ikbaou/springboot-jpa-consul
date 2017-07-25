package com.example.demo.entity.mapper;

import com.example.demo.domain.Account;
import com.example.demo.entity.AccountEntity;
import com.example.demo.util.AbstractMapper;
import com.example.demo.util.ClassUtils;

public class AccountMapper extends AbstractMapper<Account, AccountEntity> {

	@Override
	protected AccountEntity prepareEntity(AccountEntity entity, Account domain) {
		ClassUtils.setIfNotNull(entity::setId, domain::getId);
		ClassUtils.setIfNotNull(entity::setExpiryDate, domain::getExpiryDate);
		return entity;
	}

	@Override
	protected Account prepareDomain(Account domain, AccountEntity entity) {
		ClassUtils.setIfNotNull(domain::setId, entity::getId);
		ClassUtils.setIfNotNull(domain::setExpiryDate, entity::getExpiryDate);
		return domain;
	}

}
