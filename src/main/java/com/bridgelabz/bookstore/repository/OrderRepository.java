package com.bridgelabz.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bridgelabz.bookstore.model.OrderModel;

//Ability to provide CRUD operations and create table for given entity
public interface OrderRepository extends JpaRepository<OrderModel, Integer> {
	@Query(value = "select price from order_details,book where book.bookid=order_details.bookid and id =:id", nativeQuery = true)
	public Integer getPrice(Integer id);

}
