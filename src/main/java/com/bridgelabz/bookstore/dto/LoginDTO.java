package com.bridgelabz.bookstore.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginDTO {
//	@NotEmpty(message = "Please Provide email")
//	@Email
	private String email;
//	@NotBlank(message = "Please provide password")
	private String newPassword;
}
