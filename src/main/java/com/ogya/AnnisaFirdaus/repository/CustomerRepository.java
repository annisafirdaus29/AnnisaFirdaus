package com.ogya.AnnisaFirdaus.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ogya.AnnisaFirdaus.domain.Customer;



@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
	    Page<Customer> findAll(Pageable pageable);
		Page<Customer> findByGender(boolean gender, Pageable pageable);
		@Query(value = "SELECT * FROM customer WHERE first_name = ?1 OR last_name = ?1", nativeQuery = true)
		Page<Customer> findByName(String search, Pageable pageable);
	   
    
}
