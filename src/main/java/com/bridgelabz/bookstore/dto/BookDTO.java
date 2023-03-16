package com.bridgelabz.bookstore.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class BookDTO {
//	@NotEmpty(message = "Please enter book name")
	private String bookName;
//	@NotEmpty(message = "Please enter author name")
	private String authorName;
	private Integer price;
	private Integer quantity;
	private String bookDescription;
	private String bookImg;
}
