package com.example.demo.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.entity.mapper.AccountMapper;

@Configuration
public class BeanConfig{
		
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}	
	
	@Bean
	public AccountMapper accountMApper(){
		return new AccountMapper();
	}

}