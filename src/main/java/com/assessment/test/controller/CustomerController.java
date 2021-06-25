package com.assessment.test.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.test.dto.CustomerDto;
import com.assessment.test.dto.TodoDto;
import com.assessment.test.entity.Customer;
import com.assessment.test.service.CustomerService;
import com.assessment.test.service.ThirdPartyService;
import com.google.gson.Gson;

@RestController
@RequestMapping("customer")
public class CustomerController {
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ThirdPartyService thirdPartyService;
	@Autowired
	private Gson gson;

	@GetMapping(value = "/")
	public List<Customer> getCustomers() {
		logger.info("getCustomers request");
		List<Customer> customers = customerService.findAll();
		logger.info("getCustomers response > {}", gson.toJson(customers));
		return customers;
	}

	@GetMapping(value = "/paging")
	public List<Customer> getCustomerByPage(@RequestParam String page, @RequestParam String pageSize,
			@RequestParam String order) {
		logger.info("getCustomerByPage request: {}", "page: " + page + ", pageSize: " + pageSize);

		List<Customer> customers = customerService.findAllByPage(page, pageSize, order);

		logger.info("getCustomerByPage response > {}", gson.toJson(customers));
		return customers;
	}

	@GetMapping(value = "/get/{customerName}")
	public List<Customer> getCustomer(@PathVariable String name) {

		logger.info("getCustomer request: {}", name);

		List<Customer> customers = customerService.findWith(name);

		logger.info("getCustomer response > {}", gson.toJson(customers));
		return customers;
	}

	@PostMapping(value = "/create", produces = "application/json")
	public List<Customer> createCustomer(@RequestBody CustomerDto customerDto) {
		logger.info("updateCustomer request > {}", gson.toJson(customerDto));

		
		List<Customer> list = customerService.insertCustomer(customerDto);

		logger.info("createCustomer response > {}", gson.toJson(list));
		return list;
	}

	@PutMapping(value = "/update", produces = "application/json")
	public Customer updateCustomer(@RequestBody CustomerDto customerDto) {
		logger.info("updateCustomer request > {}", gson.toJson(customerDto));

		Customer customer = customerService.updateCustomer(customerDto);
		logger.info("updateCustomer response > {}", gson.toJson(customer));
		return customer;
	}

	@GetMapping("/getMockTodo/{id}")
	public TodoDto getMockTodo(@PathVariable String id) {
		return thirdPartyService.getUser(Long.valueOf(id));
	}

}