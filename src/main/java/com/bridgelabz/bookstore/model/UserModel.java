package com.bridgelabz.bookstore.model;

import com.bridgelabz.bookstore.dto.UserDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "UserDetails")
public class UserModel {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer userID;
	private String fullName;
	private String email;
	private String phoneNumber;
	private String password;

	public UserModel(UserDTO dto) {
		this.fullName = dto.getFullName();
		this.email = dto.getEmail();
		this.phoneNumber = dto.getPhoneNumber();
		this.password = dto.getPassword();

	}

	public UserModel(Integer userID, UserDTO dto) {
		this.userID = userID;
		this.fullName = dto.getFullName();
		this.email = dto.getEmail();
		this.phoneNumber = dto.getPhoneNumber();
		this.password = dto.getPassword();

	}

}
