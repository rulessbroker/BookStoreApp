package com.bridgelabz.bookstore.service;

import java.util.List;	
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.bridgelabz.bookstore.dto.ChangePasswordDTO;
import com.bridgelabz.bookstore.dto.LoginDTO;
import com.bridgelabz.bookstore.dto.UserDTO;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.model.UserModel;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.util.EmailService;
//import com.bridgelabz.bookstore.util.OTPGenerator;
import com.bridgelabz.bookstore.util.TokenUtil;


import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

//Ability to provide service to controller
@Service
@Slf4j
public class UserService implements IUserService {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	EmailService mailService;

	@Autowired
	TokenUtil util;
	
	@Autowired
//    private OTPGenerator otpGenerator;

	// Ability to serve controller's insert user record api call
	public String registerUser(UserDTO userdto) {
		Optional<UserModel> user = userRepo.findByEmail(userdto.getEmail());
		if (!user.isEmpty()) {
			throw new BookStoreException("Email is alredy Register ");
		} else {
			UserModel newUser = new UserModel(userdto);
			userRepo.save(newUser);
//			user.setOtp(otpGenerator.generateOTP());
			String message = newUser.getFullName();
			mailService.sendEmail(userdto.getEmail(), "Account Registration successfully",
					"Hello" + " Your Account has been created.");
			return message;
		}
	}

	// Ability to serve controller's user login api call
	public UserModel userLogin(LoginDTO logindto) {
		Optional<UserModel> newUser = userRepo.findByEmail(logindto.getEmail());
		if (logindto.getEmail().equals(newUser.get().getEmail())
				&& logindto.getNewPassword().equals(newUser.get().getPassword())) {
			String token = util.createToken(newUser.get().getUserID());
			mailService.sendEmail(logindto.getEmail(), "Account Sign-up successfully", "\nHello " + logindto.getEmail()
					+ " Your Account has been Loggin Successfully.Your token is " + token);
			log.info("SuccessFully Logged In");
			return newUser.get();
		} else {

			throw new BookStoreException("User doesn't exists");

		}
	}

	// Ability to serve controller's retrieve user record by token api call
	public UserModel getRecordByToken(String token) {
		Integer userIdToken = util.decodeToken(token);
		Optional<UserModel> user = userRepo.findById(userIdToken);
		if (user.isEmpty()) {
			throw new BookStoreException("User Record doesn't exists");
		} else {
			log.info("Record retrieved successfully for given token having id " + userIdToken);
			return user.get();
		}
	}

	// Ability to serve controller's retrieve all user records api call
	public List<UserModel> getAllRecords() {
		List<UserModel> userList = userRepo.findAll();
		log.info("All Record Retrieved Successfully");
		return userList;
	}

	// Ability to serve controller's retrieve user record by id api call
	public UserModel getRecord(Integer id) {
		Optional<UserModel> user = userRepo.findById(id);
		if (user.isEmpty()) {
			throw new BookStoreException("User Record doesn't exists");
		} else {
			log.info("Record retrieved successfully for id " + id);
			return user.get();
		}
	}

	// Ability to serve controller's update user record by id api call
	public UserModel updateRecord(Integer id, UserDTO dto) {
		Optional<UserModel> user = userRepo.findById(id);
		if (user.isEmpty()) {
			throw new BookStoreException("User Record doesn't exists");
		} else {
			UserModel newUser = new UserModel(id, dto);
			userRepo.save(newUser);
			log.info("User data updated successfully");
			return newUser;
		}
	}

	// Ability to serve controller's change password api call
	public UserModel changePassword(@Valid @RequestBody ChangePasswordDTO dto) {
		Optional<UserModel> user = userRepo.findByEmail(dto.getEmail());
		if (user.isEmpty()) {
			throw new BookStoreException("User doesn't exists");
		} else {
			if (dto.getEmail().equals(user.get().getEmail())) {
				user.get().setPassword(dto.getNewPassword());
				userRepo.save(user.get());
				log.info("Password changes successfully");
				mailService.sendEmail(user.get().getEmail(), "Password Change Successfully" + user.get().getFullName(),
						"Password is :" + user.get().getPassword());

				return user.get();
			} else {
				throw new BookStoreException("Invalid token");
			}
		}
	}

	// Created to serve controller's retrieve user record by email api call
	public UserModel getUserByEmailId(String email) {
		Optional<UserModel> newUser = userRepo.findByEmail(email);
		if (newUser.isEmpty()) {
			throw new BookStoreException("User record does not exist");
		} else {
			return newUser.get();
		}
	}

//	Delete User Using JWT token
	public String deleteUserByToken(String token) {
		Integer userIdToken = util.decodeToken(token);
		Optional<UserModel> user = userRepo.findById(userIdToken);
		if (user.isEmpty()) {
			throw new BookStoreException("User Record doesn't exists");
		} else {
			userRepo.deleteById(user.get().getUserID());
			log.info("Record Delete successfully for given token having id " + userIdToken);
			return "Record Delete successfully for given token id is " + token;
		}

	}
	

}
