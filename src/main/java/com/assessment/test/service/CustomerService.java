package com.assessment.test.service;

import java.util.List;

import com.assessment.test.dto.CustomerDto;

public interface CustomerService {
	List<CustomerDto> findAll();

	List<CustomerDto> findAllByPage(String page, String pageSize, String order);

	List<CustomerDto> findWith(String name);

	List<CustomerDto> insertCustomer(CustomerDto customer);

	CustomerDto updateCustomer(CustomerDto customerDto);
}
