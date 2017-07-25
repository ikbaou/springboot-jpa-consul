package com.example.demo.util;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

/**
 * @author ibaou
 *
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable{

	private static final long serialVersionUID = 1L;

}
