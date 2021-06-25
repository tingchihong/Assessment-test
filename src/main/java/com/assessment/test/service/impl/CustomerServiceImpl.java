package com.assessment.test.service.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	public List<Customer> findAll() {
		List<Customer> list = customerRepo.findAll();
		return list;
	}

	@Override
	public List<Customer> findWith(String name) {
		List<Customer> list = customerRepo.findByNameContaining(name);
		return list;
	}

	@Override
	public List<Customer> insertCustomer(CustomerDto customerDto) {
		Customer customer = new ObjectMapper().convertValue(customerDto, Customer.class);
		Long customerID = customerRepo.save(customer).getCustomerID();

		Warranty warranty = Warranty.builder().startDate(new Date(System.currentTimeMillis()))
				.customerID(customerID).build();
		warrantyRepo.save(warranty);

		return customerRepo.findAll();
	}

	@Override
	public Customer updateCustomer(CustomerDto customerDto) {
		Customer customer = new ObjectMapper().convertValue(customerDto, Customer.class);
		return customerRepo.save(customer);
	}

	@Override
	public List<Customer> findAllByPage(String page, String pageSize, String order) {
		PageRequest pageable = PageRequest.of(Integer.valueOf(page), Integer.valueOf(pageSize),
				order != null && order.equalsIgnoreCase("DESC") ? Direction.fromString("DESC")
						: Direction.fromString("ASC"),
				"name");
		return customerRepo.findAll(pageable).toList();
	}
}