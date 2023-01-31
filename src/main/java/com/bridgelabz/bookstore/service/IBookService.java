package com.bridgelabz.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.dto.QuantityDTO;
import com.bridgelabz.bookstore.model.BookModel;

@Component
public interface IBookService {
	public BookModel insertBook(BookDTO bookdto);

	public List<BookModel> getAllBookRecords();

	public Optional<BookModel> getBookRecord(Integer id);

	public BookModel updateBookRecord(Integer id, BookDTO dto);

	public List<BookModel> getRecordByBookName(String bookName);

	public BookModel deleteBookRecord(Integer id);

	public List<BookModel> sortRecordDesc();

	public List<BookModel> sortRecordAsc();

	public BookModel updateQuantity(QuantityDTO quantity);
}
