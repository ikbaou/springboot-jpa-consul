package com.example.demo.util;

public interface BeanResolver {
	
	public <T> T getBean(Class<T> beanClass);

}
