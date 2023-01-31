package com.bridgelabz.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstore.model.CartModel;

@Repository
public interface CartRepository extends JpaRepository<CartModel, Integer> {
	@Query(value = "select * from cart where bookid =:bookId", nativeQuery = true)
	List<CartModel> findByBookId(Integer bookId);

	@Query(value = "select * from cart where userid =:userId", nativeQuery = true)
	List<CartModel> findByUserId(Integer userId);

	@Query(value = "select * from cart where userid =:userId and bookid =:bookId", nativeQuery = true)
	List<CartModel> findByUserAndBookId(Integer userId, Integer bookId);

	@Query(value = "select * from cart where userid =:userID", nativeQuery = true)
	List<CartModel> findByUserID(Integer userID);

}
