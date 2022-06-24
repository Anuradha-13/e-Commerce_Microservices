package com.spring.order.repo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.order.dto.ProductQueryDetails;
import com.spring.order.dto.ProductResponse;
import com.spring.order.entity.Order;


@Repository
public interface OrderRepo extends JpaRepository<Order,Integer> {
	
	@Query("SELECT new com.spring.order.dto.ProductQueryDetails(o.orderId,op.productId) FROM Order o JOIN o.orderProducts op")
	public List<ProductQueryDetails> getProductDetailsJoin();
			
	List<Order> findAllByDateCreatedBetween(LocalDate startDate, LocalDate endDate);

	public Optional<List<Order>> findByUserId(int userId);
}
