package com.assessment.test.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.assessment.test.entity.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {

	@Query(value = "SELECT * FROM customer c WHERE c.name LIKE ?1", nativeQuery = true)
	List<Customer> findByNameContaining(String name);

}
