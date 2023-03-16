package com.bridgelabz.bookstore.controller;

import java.util.List;

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

import com.bridgelabz.bookstore.dto.CartDTO;
import com.bridgelabz.bookstore.dto.QuantityDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.model.BookModel;
import com.bridgelabz.bookstore.model.CartModel;
import com.bridgelabz.bookstore.model.UserModel;
import com.bridgelabz.bookstore.service.ICartService;

import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("bookstore/cart")
public class CartController {
	// Autowired ICartService to inject its dependency here
	@Autowired
	ICartService cartService;

	// Ability to call api to insert all cart records
	@PostMapping("/insert")
	public ResponseEntity<ResponseDTO> insertBook(@Valid @RequestBody CartDTO cartdto) {
		ResponseDTO dto = new ResponseDTO("Book Added To Cart successfully !", cartService.insertCart(cartdto));
		System.out.println("Cart record updated successfully  " + dto);
		return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}

	// Ability to call api to retrieve all card records
	@GetMapping("/retrieveAllCarts")
	public ResponseEntity<ResponseDTO> getAllCartRecords() {
		ResponseDTO dto = new ResponseDTO("All records retrieved successfully !", cartService.getAllCartRecords());
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	// Ability to call api to retrieve cart record by id
	@GetMapping("/retrieveCart/{id}")
	public ResponseEntity<ResponseDTO> getBookRecord(@PathVariable Integer id) {
		ResponseDTO dto = new ResponseDTO("Record retrieved successfully !", cartService.getCartRecord(id));
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	// Ability to call api to update cart by id
	@PutMapping("/updateCart/{id}")
	public ResponseEntity<ResponseDTO> updateCartRecord(@PathVariable Integer id, @Valid @RequestBody CartDTO cartdto) {
		ResponseDTO dto = new ResponseDTO("Record updated successfully !", cartService.updateCartRecord(id, cartdto));
		return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
	}

	// Ability to call api to update quantity of book in cart by id
	@PutMapping("/updateQuantity/{id}")
	public ResponseEntity<ResponseDTO> updateQuantity(@Valid @RequestBody QuantityDTO newQuantity) {
		ResponseDTO dto = new ResponseDTO("Quantity for book record updated successfully !",
				cartService.updateQuantity(newQuantity));
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	// Ability to call api to delete cart by id
	@DeleteMapping("/deleteCart/{id}")
	public ResponseEntity<ResponseDTO> deleteCartRecord(@PathVariable Integer id) {
		ResponseDTO dto = new ResponseDTO("Record deleted successfully !", cartService.deleteCartRecord(id));
		return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
	}

	// Ability to call api to delete All cart
	@DeleteMapping("/deleteall")
	public ResponseEntity<ResponseDTO> deleteBooks() {
		List<CartModel> books = cartService.deleteAllFromCart();
		return new ResponseEntity(books, HttpStatus.OK);
	}

//	Upadate cart quantity using id
	@PutMapping("/updateCartbyuserid/{id}/{quantity}")
	public ResponseEntity<ResponseDTO> updateCartRecordByUserID(@PathVariable Integer id,
			@PathVariable Integer quantity, @Valid @RequestBody BookModel book, @Valid @RequestBody UserModel user) {
		ResponseDTO dto = new ResponseDTO("Record updated successfully By using UserID!",
				cartService.updateCartRecordByUserID(id, quantity, book, user));
		return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
	}

}
