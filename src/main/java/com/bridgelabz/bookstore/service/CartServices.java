package com.bridgelabz.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.dto.CartDTO;
import com.bridgelabz.bookstore.dto.QuantityDTO;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.model.BookModel;
import com.bridgelabz.bookstore.model.CartModel;
import com.bridgelabz.bookstore.model.UserModel;
import com.bridgelabz.bookstore.repository.BookRepository;
import com.bridgelabz.bookstore.repository.CartRepository;
import com.bridgelabz.bookstore.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CartServices implements ICartService {
	@Autowired
	private CartRepository cartRepo;
	@Autowired
	private BookRepository bookRepo;
	@Autowired
	private UserRepository userRepo;

	// Ability to serve to controller's insert cart details api call
	public CartModel insertCart(CartDTO cartdto) {
		// check the already in cart or not
		Optional<BookModel> book = bookRepo.findById(cartdto.getBookID());
		Optional<UserModel> user = userRepo.findById(cartdto.getUserID());

		if (book.isPresent() && user.isPresent()) {
			if (cartdto.getQuantity() <= book.get().getQuantity()) {
				CartModel newCart = new CartModel(cartdto.getQuantity(), book.get(), user.get());
				cartRepo.save(newCart);
				log.info("Cart record inserted successfully");
				bookRepo.save(book.get());
				return newCart;
			} else {
				throw new BookStoreException("Requested quantity is not available");
			}
		} else {
			throw new BookStoreException("Book or User doesn't exists");
		}
	}

	// Ability to serve to controller's retrieve all cart records api call
	public List<CartModel> getAllCartRecords() {
		List<CartModel> cartList = cartRepo.findAll();
		log.info("All cart records retrieved successfully");
		return cartList;
	}

	// Ability to serve to controller's retrieve cart record by id api call
	public CartModel getCartRecord(Integer id) {
		Optional<CartModel> cart = cartRepo.findById(id);
		if (cart.isEmpty()) {
			throw new BookStoreException("Cart Record doesn't exists");
		} else {
			log.info("Cart record retrieved successfully for id " + id);
			return cart.get();
		}
	}

	// Ability to serve to controller's update cart record by id api call
	public CartModel updateCartRecord(Integer id, CartDTO dto) {
		Optional<CartModel> cart = cartRepo.findById(id);
		Optional<BookModel> book = bookRepo.findById(dto.getBookID());
		Optional<UserModel> user = userRepo.findById(dto.getUserID());
		if (cart.isEmpty()) {
			throw new BookStoreException("Cart Record doesn't exists");
		} else {
			if (book.isPresent() && user.isPresent()) {
				if (dto.getQuantity() <= book.get().getQuantity()) {
					CartModel newCart = new CartModel(id, dto.getQuantity(), book.get(), user.get());
					cartRepo.save(newCart);
					log.info("Cart record updated successfully for id " + id);
					bookRepo.save(book.get());
					return newCart;
				} else {
					throw new BookStoreException("Requested quantity is not available");
				}
			} else {
				throw new BookStoreException("Book or User doesn't exists");
			}
		}
	}

	// Ability to serve to controller's delete cart record by id api call
	public CartModel deleteCartRecord(Integer id) {
		Optional<CartModel> cart = cartRepo.findById(id);
		Optional<BookModel> book = bookRepo.findById(cart.get().getBook().getBookId());
		if (cart.isEmpty()) {
			throw new BookStoreException("Cart Record doesn't exists");
		} else {
			bookRepo.save(book.get());
			cartRepo.deleteById(id);
			log.info("Cart record deleted successfully for id " + id);
			return cart.get();
		}
	}

	// Ability to serve to controller's update quantity of books in cart api call
	public CartModel updateQuantity(QuantityDTO quantity) {
		Optional<CartModel> cart = cartRepo.findById(quantity.getId());
		Optional<BookModel> book = bookRepo.findById(cart.get().getBook().getBookId());
		if (cart.isEmpty()) {
			throw new BookStoreException("Cart Record doesn't exists");
		} else {
			if (quantity.getNewQuantity() <= book.get().getQuantity()) {
				cart.get().setQuantity(quantity.getNewQuantity());
				cartRepo.save(cart.get());
				log.info("Quantity in cart record updated successfully");
				bookRepo.save(book.get());
				return cart.get();
			} else {
				throw new BookStoreException("Requested quantity is not available");
			}
		}
	}

	// Created service method which serves controller api to delete all quantity
	public List<CartModel> deleteAllFromCart() {
		cartRepo.deleteAll();
		return cartRepo.findAll();
	}

	public CartModel updateCartRecordByUserID(Integer id, Integer quantity, BookModel bookModel, UserModel userModel) {
		List<CartModel> cart = cartRepo.findByUserID(id);
		if (cart.isEmpty()) {
			throw new BookStoreException("Cart Record doesn't exists");
		} else {
			CartModel cartModel = new CartModel(id, quantity, bookModel, userModel);
			cartRepo.save(cartModel);
			log.info("cart record updated successfully");
			return cartModel;
		}
	}

}
