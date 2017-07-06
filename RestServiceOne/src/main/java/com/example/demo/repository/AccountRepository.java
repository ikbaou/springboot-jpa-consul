package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.AccountEntity;
import com.example.demo.entity.CustomerEntity;

@Transactional(readOnly = true)
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    List<AccountEntity> findByCustomer(CustomerEntity customer); 
}
