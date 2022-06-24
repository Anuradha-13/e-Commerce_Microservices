package com.spring.order.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.order.entity.OrderProducts;

@Repository
public interface OrderProductsRepo extends JpaRepository<OrderProducts,Integer>{

}
