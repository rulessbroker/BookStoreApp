package com.bridgelabz.bookstore.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

//Order Model with fields
@Entity
@Data
@NoArgsConstructor
@Table(name = "OrderDetails")
public class OrderModel {
	@Id
	@GeneratedValue
	private Integer orderID;
	private LocalDate date = LocalDate.now();
	private Integer price;
	private Integer quantity;
	private String address;
	@OneToOne
	@JoinColumn(name = "userID")
	private UserModel user;
	@OneToOne
	@JoinColumn(name = "bookID")
	private BookModel book;
	private boolean cancel;

	public OrderModel(Integer orderID, Integer quantity, String address, BookModel book, UserModel user,
			boolean cancel) {
		this.orderID = orderID;
		this.price = quantity * book.getPrice();
		this.quantity = quantity;
		this.address = address;
		this.book = book;
		this.user = user;
		this.cancel = cancel;
	}

	public OrderModel(Integer quantity, String address, BookModel book, UserModel user, boolean cancel) {
		this.price = quantity * book.getPrice();
		this.quantity = quantity;
		this.address = address;
		this.book = book;
		this.user = user;
		this.cancel = cancel;
	}
}
