package com.assessment.test.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Setter
@Getter
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long customerID;

	@Column(name = "name")
	private String name;
	@Column(name = "mobile_no")
	private String mobileNo;

	@Column(name = "age")
	private int age;

}
