package com.ogya.AnnisaFirdaus.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ogya.AnnisaFirdaus.domain.Product;



import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
	    Page<Product> findAll(Pageable pageable);
	    Page<Product> findByName(String name, Pageable pageable);
	    Page<Product> findByBrand(String brand, Pageable pageable);
	    Page<Product> findByMadein(String madein, Pageable pageable);
	    List<Product> findByBrand(String brand);
	    List<Product> findByMadein(String madein);
    
}
