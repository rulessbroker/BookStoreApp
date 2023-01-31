package com.bridgelabz.bookstore.model;

import com.bridgelabz.bookstore.dto.CartDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class CartModel {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer cartID;
	@OneToOne()
	@JoinColumn(name = "userID")
	private UserModel user;
	@ManyToOne()
	@JoinColumn(name = "bookID")
	private BookModel book;
	private Integer quantity;

	private Integer totalPrice;
	
	
	public CartModel(Integer cartID, Integer quantity, BookModel book, UserModel user) {
		this.cartID = cartID;
		this.quantity = quantity;
		this.book = book;
		this.user = user;
		this.totalPrice = quantity * book.getPrice();
	}

	public CartModel(Integer quantity, BookModel book, UserModel user) {
		this.quantity = quantity;
		this.book = book;
		this.user = user;
		this.totalPrice = quantity * book.getPrice();
	}
}
