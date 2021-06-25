package com.assessment.test.service;

import java.util.List;

import com.assessment.test.dto.CustomerDto;
import com.assessment.test.entity.Customer;

public interface CustomerService {
	List<Customer> findAll();

	List<Customer> findAllByPage(String page, String pageSize, String order);

	List<Customer> findWith(String name);

	List<Customer> insertCustomer(CustomerDto customer);

	Customer updateCustomer(CustomerDto customerDto);
}
