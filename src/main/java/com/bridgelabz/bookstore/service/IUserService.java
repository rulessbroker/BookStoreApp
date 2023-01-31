package com.bridgelabz.bookstore.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.dto.ChangePasswordDTO;
import com.bridgelabz.bookstore.dto.LoginDTO;
import com.bridgelabz.bookstore.dto.UserDTO;
import com.bridgelabz.bookstore.model.UserModel;

@Component
public interface IUserService {
	public String registerUser(UserDTO userdto);

	public UserModel userLogin(LoginDTO logindto);

	public List<UserModel> getAllRecords();

	public UserModel getRecord(Integer id);

	public UserModel getRecordByToken(String token);

	public UserModel updateRecord(Integer id, UserDTO dto);

	public UserModel changePassword(ChangePasswordDTO dto);

	public UserModel getUserByEmailId(String email);

	public String deleteUserByToken(String token);
}
