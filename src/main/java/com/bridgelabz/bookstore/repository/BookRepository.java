package com.bridgelabz.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstore.model.BookModel;

@Repository
public interface BookRepository extends JpaRepository<BookModel, Integer> {
	
	public List<BookModel> findByBookName(String bookName);

	@Query(value = "select * from book_model ORDER BY price", nativeQuery = true)
	public List<BookModel> sortBooksAsc();

	@Query(value = "select * from book_model ORDER BY price DESC", nativeQuery = true)
	public List<BookModel> sortBooksDesc();

}
