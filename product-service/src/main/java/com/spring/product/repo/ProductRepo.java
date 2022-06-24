package com.spring.product.repo;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.product.entity.Product;

public interface ProductRepo extends JpaRepository<Product,Integer> {

	List<Product> findByNameIgnoreCaseContaining(String name);

}
