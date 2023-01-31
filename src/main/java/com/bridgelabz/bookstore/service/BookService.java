package com.bridgelabz.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.dto.QuantityDTO;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.model.BookModel;
import com.bridgelabz.bookstore.repository.BookRepository;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

//Ability to provide service to controllers api calls
@Service
@Slf4j
public class BookService implements IBookService {
	// Autowired BookRepository to inject its dependency here
	@Autowired
	private BookRepository bookRepo;

	// Ability to serve to controller's insert api call
	@Override
	public BookModel insertBook(BookDTO bookdto) {
		BookModel newBook = new BookModel(bookdto);
		log.info("Book record inserted successfully");
		return bookRepo.save(newBook);
	}

	// Ability to serve to controller's retrieving all records api call
	@Override
	public List<BookModel> getAllBookRecords() {
		List<BookModel> bookList = bookRepo.findAll();
		log.info("All book records retrieved successfully");
		return bookList;
	}

	// Ability to serve to controller's retrieving all records api call
	@Override
	public Optional<BookModel> getBookRecord(Integer id) {
		Optional<BookModel> book = bookRepo.findById(id);
		if (book.isEmpty()) {
			throw new BookStoreException("Book Record doesn't exists");
		} else {
			log.info("Book record retrieved successfully for id " + id);
			return book;
		}
	}

	// Ability to serve to controller's update record by id api call
	@Override
	public BookModel updateBookRecord(Integer id, BookDTO dto) {
		Optional<BookModel> book = bookRepo.findById(id);
		if (book.isEmpty()) {
			throw new BookStoreException("Book Record doesn't exists");
		} else {
			BookModel newBook = new BookModel(id, dto);
			bookRepo.save(newBook);
			log.info("Book record updated successfully for id " + id);
			return newBook;
		}

	}

	// Ability to serve to controller's retrieve record by book name api call
	@Override
	public List<BookModel> getRecordByBookName(String bookName) {
		List<BookModel> book = bookRepo.findByBookName(bookName);
		if (book.isEmpty()) {
			throw new BookStoreException("Book doesn't exists");
		} else {
			log.info("Book record retrieved successfully for Book Name : " + bookName);
			return book;
		}
	}

	// Ability to serve to controller's delete record api call
	@Override
	public BookModel deleteBookRecord(Integer id) {
		Optional<BookModel> book = bookRepo.findById(id);
		if (book.isEmpty()) {
			throw new BookStoreException("Book Record doesn't exists");
		} else {
			bookRepo.deleteById(id);
			log.info("Book record deleted successfully for id " + id);
			return book.get();
		}
	}

	// Ability to serve to controller's sort all records in ascending order api call
	@Override
	public List<BookModel> sortRecordAsc() {
		List<BookModel> listOfBooks = bookRepo.sortBooksAsc();
		log.info("Book records sorted in ascending order by price successfully");
		return listOfBooks;
	}

	// Ability to serve to controller's sort all records in descending order api
	// call
	@Override
	public List<BookModel> sortRecordDesc() {
		List<BookModel> listOfBooks = bookRepo.sortBooksDesc();
		log.info("Book records sorted in descending order by price successfully");
		return listOfBooks;
	}

	// Ability to serve to controller's update book quantity api call
	@Override
	public BookModel updateQuantity(@Valid @RequestBody QuantityDTO quantity) {
		Optional<BookModel> book = bookRepo.findById(quantity.getId());
		if (book.isEmpty()) {
			throw new BookStoreException("Book Record doesn't exists");
		} else {
			book.get().setQuantity(quantity.getNewQuantity());
			bookRepo.save(book.get());
			log.info("Quantity for book record updated successfully for id " + quantity.getId());
			return book.get();
		}
	}
}
