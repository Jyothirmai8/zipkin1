package com.zipkin1.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import org.springframework.cloud.sleuth.sampler.AlwaysSampler;



@RestController
public class ConsumerController {
	
	@Autowired
	private RestTemplate rt;
	
	
	@Bean
	public RestTemplate gett() {
		return new RestTemplate();
	}
	
	
	@Bean
	public AlwaysSampler alwaysSampler() {
		return new AlwaysSampler();
	}
	
		
	@RequestMapping("allEmployee4")
	public String displayAllEmployee(){
		
		RestTemplate client=new RestTemplate();
		ResponseEntity<String> result=null;
		result=client.exchange("http://localhost:10002/allEmployee3",HttpMethod.GET,createHeader(),String.class);
					
		return result.getBody();
	}
	
	public static HttpEntity<?> createHeader(){
		HttpHeaders h=new HttpHeaders();
		h.set("Accept",MediaType.APPLICATION_JSON_VALUE);
		
		return new HttpEntity<>(h);
	}

}
