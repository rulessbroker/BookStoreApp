package com.bridgelabz.bookstore.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CartDTO {
	private Integer userID;
	private Integer bookID;
	@NotNull(message = "Book quantity yet to be provided")
	private Integer quantity;
}
