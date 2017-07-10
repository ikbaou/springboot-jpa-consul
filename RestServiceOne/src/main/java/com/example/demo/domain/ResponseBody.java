package com.example.demo.domain;

import java.util.ArrayList;
import java.util.List;

public class ResponseBody<E> {
	
	private E entity;
	
	private List<Message> errors;
	private List<Message> warnings;
	private List<Message> infos;
	private List<Message> successes;
	
	public ResponseBody(){
		this.errors = new ArrayList<>();
		this.warnings = new ArrayList<>();
		this.infos = new ArrayList<>();
		this.successes = new ArrayList<>();
	}
	
	/*
	 * Util
	 */
	public ResponseBody<E> addError(Message msg){
		msg.setType(Message.Level.ERROR);
		addMessage(msg, errors);
		return this;
	}
	
	public ResponseBody<E> addSuccess(Message msg){
		msg.setType(Message.Level.SUCCESS);
		addMessage(msg, successes);
		return this;
	}
	
	public ResponseBody<E> entity(E entity){
		setEntity(entity);
		return this;		
	}
	
	protected void addMessage(Message msg, List<Message> messages){
		if(messages==null){
			messages = new ArrayList<>();
		}
		messages.add(msg);		
	}
	
	/*
	 * Getters / Setters
	 */

	public List<Message> getErrors() {
		return errors;
	}

	public E getEntity() {
		return entity;
	}

	public void setEntity(E entity) {
		this.entity = entity;
	}

	public void setErrors(List<Message> errors) {
		this.errors = errors;
	}

	public List<Message> getWarnings() {
		return warnings;
	}

	public void setWarnings(List<Message> warnings) {
		this.warnings = warnings;
	}

	public List<Message> getInfos() {
		return infos;
	}

	public void setInfos(List<Message> infos) {
		this.infos = infos;
	}

	public List<Message> getSuccesses() {
		return successes;
	}

	public void setSuccesses(List<Message> successes) {
		this.successes = successes;
	}

}
