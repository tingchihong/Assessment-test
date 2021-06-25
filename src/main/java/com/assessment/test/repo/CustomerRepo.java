package com.assessment.test.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assessment.test.entity.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
	List<Customer> findByNameContaining(String name);
	
}
