package com.example.demo.spring.springfox.swagger.web;

import java.util.HashMap;
import java.util.Map;

public class GroupDocumentationMapper {
	
	private Map<String, String> groupDocMap;
	
	public GroupDocumentationMapper(){
		this.groupDocMap = new HashMap<>();
	}
	
	public GroupDocumentationMapper addToMap(String group, String docUrl){
		if(this.groupDocMap==null){this.groupDocMap = new HashMap<>();}
		this.groupDocMap.put(group, docUrl);
		return this;
	}

	public Map<String, String> getGroupDocMap() {
		return groupDocMap;
	}

	public void setGroupDocMap(Map<String, String> groupDocMap) {
		this.groupDocMap = groupDocMap;
	}	

}
