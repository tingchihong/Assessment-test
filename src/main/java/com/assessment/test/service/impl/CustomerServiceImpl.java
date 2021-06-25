package com.assessment.test.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.test.dto.CustomerDto;
import com.assessment.test.entity.Customer;
import com.assessment.test.entity.Warranty;
import com.assessment.test.repo.CustomerRepo;
import com.assessment.test.repo.WarrantyRepo;
import com.assessment.test.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepo customerRepo;
	@Autowired
	private WarrantyRepo warrantyRepo;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<CustomerDto> findAll() {
		List<CustomerDto> listCustomer = Arrays
				.asList(new ObjectMapper().convertValue(customerRepo.findAll(), CustomerDto[].class));
		return listCustomer;
	}

	@Override
	public List<CustomerDto> findWith(String name) {
		List<CustomerDto> listCustomer = Arrays
				.asList(new ObjectMapper().convertValue(customerRepo.findByNameContaining(name), CustomerDto[].class));
		return listCustomer;
	}

	@Override
	public List<CustomerDto> insertCustomer(CustomerDto customerDto) {
		Customer customer = new ObjectMapper().convertValue(customerDto, Customer.class);
		Long customerID = customerRepo.save(customer).getCustomerID();

		Warranty warranty = Warranty.builder().startDate(new Date(System.currentTimeMillis())).customerID(customerID)
				.build();
		warrantyRepo.save(warranty);

		List<CustomerDto> listCustomer = Arrays
				.asList(new ObjectMapper().convertValue(customerRepo.findAll(), CustomerDto[].class));

		return listCustomer;
	}

	@Override
	public CustomerDto updateCustomer(CustomerDto customerDto) {
		Customer customer = new ObjectMapper().convertValue(customerDto, Customer.class);
		CustomerDto latestCustomerDetail = new ObjectMapper().convertValue(customerRepo.save(customer), CustomerDto.class);
		return latestCustomerDetail;
	}

	@Override
	public List<CustomerDto> findAllByPage(String page, String pageSize, String order) {
		PageRequest pageable = PageRequest.of(Integer.valueOf(page), Integer.valueOf(pageSize),
				order != null && order.equalsIgnoreCase("DESC") ? Direction.fromString("DESC")
						: Direction.fromString("ASC"),
				"name");

		List<CustomerDto> listCustomer = Arrays
				.asList(new ObjectMapper().convertValue(customerRepo.findAll(pageable).toList(), CustomerDto[].class));
		return listCustomer;
	}
}