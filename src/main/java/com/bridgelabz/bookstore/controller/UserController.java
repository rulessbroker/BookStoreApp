package com.bridgelabz.bookstore.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.dto.ChangePasswordDTO;
import com.bridgelabz.bookstore.dto.LoginDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.dto.UserDTO;
import com.bridgelabz.bookstore.service.IUserService;

import jakarta.validation.Valid;

//Controller to make api calls
@CrossOrigin("*")
@RestController
@RequestMapping("/bookstore/user")
public class UserController {
	// Autowired IUserService to inject its dependency here
	@Autowired
	private IUserService userService;

	// Ability to call api to register user
	@PostMapping("/register")
	public ResponseEntity<ResponseDTO> registerUser(@Valid @RequestBody UserDTO userdto) {
		ResponseDTO dto = new ResponseDTO("User Record created successfully !", userService.registerUser(userdto));
		return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}

	// Ability to call api to login user
	@PostMapping("/login")
	public ResponseEntity<ResponseDTO> userLogin(@Valid @RequestBody LoginDTO logindto) {
		ResponseDTO dto = new ResponseDTO("User logged in successfully !", userService.userLogin(logindto));
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	// Ability to call api to change password
	@PutMapping("/changepassword")
	public ResponseEntity<ResponseDTO> changePassword(@Valid @RequestBody ChangePasswordDTO passwordDTO) {
		ResponseDTO dto = new ResponseDTO("Password Resetted successfully !", userService.changePassword(passwordDTO));
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	// Ability to call api to retrieve all user records
	@GetMapping("/retrieveAll")
	public ResponseEntity<ResponseDTO> getAllRecords() {
		ResponseDTO dto = new ResponseDTO("All records retrieved successfully !", userService.getAllRecords());
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	// Ability to call api to retrieve user record by id
	@GetMapping("/retrieve/{id}")
	public ResponseEntity<ResponseDTO> getRecord(@PathVariable Integer id) {
		ResponseDTO dto = new ResponseDTO("Record retrieved successfully !", userService.getRecord(id));
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	// Ability to call api to update user record by id
	@PutMapping("/update/{id}")
	public ResponseEntity<ResponseDTO> updateRecord(@PathVariable Integer id, @Valid @RequestBody UserDTO userdto) {
		ResponseDTO dto = new ResponseDTO("Record updated successfully !", userService.updateRecord(id, userdto));
		return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
	}

	// Ability to call api to retrieve user record by token
	@GetMapping("/retrieveByToken/{token}")
	public ResponseEntity<ResponseDTO> getRecordByToken(@PathVariable String token) {
		ResponseDTO dto = new ResponseDTO("Record retrieved successfully !", userService.getRecordByToken(token));
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	// Ability to call api to retrieve user record by email
	@GetMapping("/retrieveByemail/{email}")
	public ResponseEntity<ResponseDTO> getUserByEmailId(@PathVariable String email) {
		ResponseDTO responseDTO = new ResponseDTO("Record for email successfully", userService.getUserByEmailId(email));
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}
	
//	Delete User By token
	@DeleteMapping("/deletebytoken/{token}")
	public ResponseEntity<ResponseDTO> deleteRecordByToken(@PathVariable String token) {
		ResponseDTO dto = new ResponseDTO("Record Delete Successfully !", userService.deleteUserByToken(token));
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

}
