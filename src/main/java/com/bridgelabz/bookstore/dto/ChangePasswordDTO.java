package com.bridgelabz.bookstore.dto;

import lombok.Data;

@Data
public class ChangePasswordDTO {
	private String email;
	private String newPassword;
}
