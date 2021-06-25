package com.assessment.test.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("customerID")
	private long customerID;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("name")
	private String name;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("mobileNo")
	private String mobileNo;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("age")
	private int age;

}