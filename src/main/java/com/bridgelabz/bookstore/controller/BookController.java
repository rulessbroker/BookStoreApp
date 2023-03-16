package com.bridgelabz.bookstore.controller;

import java.util.List;	

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.dto.QuantityDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.model.BookModel;
import com.bridgelabz.bookstore.service.IBookService;

import jakarta.validation.Valid;

//Controller class to make api calls
@CrossOrigin("*")
@RestController
@RequestMapping("/bookstore")
public class BookController {
	@Autowired
	private IBookService bookService;
	
	// Ability to call api to insert Book record
	@PostMapping("/insert")
	public ResponseEntity<ResponseDTO> insertBook(@Valid @RequestBody BookDTO bookdto) {
		ResponseDTO dto = new ResponseDTO("Book registered successfully !", bookService.insertBook(bookdto));
		return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}

	// Ability to call api to retrieve all book records
	@GetMapping("/retrieveAllBooks")
	public ResponseEntity<ResponseDTO> getAllBookRecords() {
		List<BookModel> newBook = bookService.getAllBookRecords();
		ResponseDTO dto = new ResponseDTO("All records retrieved successfully !", newBook);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	// Ability to call api to retrieve record by book name
	@GetMapping("/retrieve/{bookName}")
	public ResponseEntity<ResponseDTO> getRecordByBookName(@PathVariable String bookName) {
		List<BookModel> newBook = bookService.getRecordByBookName(bookName);
		ResponseDTO dto = new ResponseDTO("Record for particular book retrieved successfully !", newBook);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	// Ability to call api to get record by id
	@GetMapping("/retrieveBook/{id}")
	public ResponseEntity<ResponseDTO> getBookRecord(@PathVariable Integer id) {
		ResponseDTO dto = new ResponseDTO("Record retrieved successfully !", bookService.getBookRecord(id));
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	// Ability to call api to update book record by id
	@PutMapping("/updateBook/{id}")
	public ResponseEntity<ResponseDTO> updateBookRecord(@PathVariable Integer id, @Valid @RequestBody BookDTO bookdto) {
		ResponseDTO dto = new ResponseDTO("Record updated successfully !", bookService.updateBookRecord(id, bookdto));
		return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
	}

	// Ability to call api to delete book record by id
	@DeleteMapping("/deleteBook/{id}")
	public ResponseEntity<ResponseDTO> deleteBookRecord(@PathVariable Integer id) {
		ResponseDTO dto = new ResponseDTO("Record deleted successfully !", bookService.deleteBookRecord(id));
		return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
	}

	// Ability to call api to sort book records in ascending order
	@GetMapping("/sortAsc")
	public ResponseEntity<ResponseDTO> sortRecordAsc() {
		List<BookModel> newBook = bookService.sortRecordAsc();
		ResponseDTO dto = new ResponseDTO("Records for book sorted in ascending order successfully !", newBook);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	// Ability to call api to sort book records in descending order
	@GetMapping("/sortDesc")
	public ResponseEntity<ResponseDTO> sortRecordDesc() {
		List<BookModel> newBook = bookService.sortRecordDesc();
		ResponseDTO dto = new ResponseDTO("Records for book sorted in descending order successfully !", newBook);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	/// Ability to call api to update quantity of books by id
	@PutMapping("/updateQuantity")
	public ResponseEntity<ResponseDTO> updateQuantity(@Valid @RequestBody QuantityDTO quantity) {
		BookModel newBook = bookService.updateQuantity(quantity);
		ResponseDTO dto = new ResponseDTO("Quantity for book record updated successfully !", newBook);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
}
