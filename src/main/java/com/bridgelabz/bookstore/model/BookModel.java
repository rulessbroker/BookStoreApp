package com.bridgelabz.bookstore.model;

import com.bridgelabz.bookstore.dto.BookDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class BookModel {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer bookID;
	private String bookName;
	private String authorName;
	private Integer price;
	private Integer quantity;
	private String bookDescription;
	private String bookImg;

	public BookModel(BookDTO dto) {
		this.bookName = dto.getBookName();
		this.authorName = dto.getAuthorName();
		this.price = dto.getPrice();
		this.quantity = dto.getQuantity();
		this.bookDescription = dto.getBookDescription();
		this.bookImg = dto.getBookImg();
	}

	public BookModel(Integer id, BookDTO dto) {
		this.bookID = id;
		this.bookName = dto.getBookName();
		this.authorName = dto.getAuthorName();
		this.price = dto.getPrice();
		this.quantity = dto.getQuantity();
		this.bookDescription = dto.getBookDescription();
		this.bookImg = dto.getBookImg();
	}
}
