package com.bridgelabz.bookstore.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class OrderDTO {
	private Integer quantity;
//	@NotEmpty(message = "Please provide address")
	private String address;
	private Integer userID;
	private Integer bookID;
	private boolean cancel;
	private Integer cartID;
}
