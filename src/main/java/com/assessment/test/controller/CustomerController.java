package com.assessment.test.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
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
	public List<CustomerDto> getCustomers() {
		logger.info("getCustomers request");
		List<CustomerDto> customers = customerService.findAll();
		logger.info("getCustomers response > {}", gson.toJson(customers));
		return customers;
	}

	@GetMapping(value = "/getCustomerByPage")
	public List<CustomerDto> getCustomerByPage(@RequestParam String page, @RequestParam String pageSize,
			@RequestParam String order) {
		logger.info("getCustomerByPage request: {}", "page: " + page + ", pageSize: " + pageSize);

		List<CustomerDto> customers = customerService.findAllByPage(page, pageSize, order);

		logger.info("getCustomerByPage response > {}", gson.toJson(customers));
		return customers;
	}

	@GetMapping(value = "/get/{customerName}")
	public List<CustomerDto> getCustomer(@PathVariable String name) {

		logger.info("getCustomer request: {}", name);

		List<CustomerDto> customers = customerService.findWith(name);

		logger.info("getCustomer response > {}", gson.toJson(customers));
		return customers;
	}

	@PostMapping(value = "/create", produces = "application/json")
	public List<CustomerDto> create(@RequestBody CustomerDto customerDto) {
		logger.info("updateCustomer request > {}", gson.toJson(customerDto));

		List<CustomerDto> list = customerService.insertCustomer(customerDto);

		logger.info("createCustomer response > {}", gson.toJson(list));
		return list;
	}

	@PutMapping(value = "/update", produces = "application/json")
	public CustomerDto update(@RequestBody CustomerDto customerDto) {
		logger.info("updateCustomer request > {}", gson.toJson(customerDto));

		CustomerDto customer = customerService.updateCustomer(customerDto);
		logger.info("updateCustomer response > {}", gson.toJson(customer));
		return customer;
	}

	@PostMapping("/getMockTodo")
	public String getMockTodo(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			JSONObject jsonResponse = new JSONObject();
			if (request.getParameter("userId") != null) {
				long userId = Long.valueOf(request.getParameter("userId"));
				logger.info("getMockTodo request > {}", userId);
				TodoDto todoDto = thirdPartyService.getUser(userId);
				jsonResponse.put("todo", new JSONObject(gson.toJson(todoDto)));
			} else {
				logger.info("getMockTodo request > all");
				TodoDto[] todoDtoList = thirdPartyService.getUserList();
				jsonResponse.put("todoList", todoDtoList);
			}

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");

			logger.info("getMockTodo response > {}", jsonResponse.toString());
			response.getWriter().print(jsonResponse.toString());
		} catch (IOException io) {
			logger.error("getMockTodo exception > {}", io.getMessage());
			io.printStackTrace();
		}

		return null;
	}

}