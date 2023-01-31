package com.bridgelabz.bookstore.service;

import java.util.List;

import com.bridgelabz.bookstore.dto.CartDTO;
import com.bridgelabz.bookstore.dto.QuantityDTO;
import com.bridgelabz.bookstore.model.BookModel;
import com.bridgelabz.bookstore.model.CartModel;
import com.bridgelabz.bookstore.model.UserModel;

public interface ICartService {

	public CartModel insertCart(CartDTO cartdto);

	public List<CartModel> getAllCartRecords();

	public CartModel getCartRecord(Integer id);

	public CartModel updateCartRecord(Integer id, CartDTO dto);

	public CartModel deleteCartRecord(Integer id);

	public CartModel updateQuantity(QuantityDTO quantity);

	public List<CartModel> deleteAllFromCart();

	public CartModel updateCartRecordByUserID(Integer id, Integer quantity, BookModel bookModel, UserModel userModel);
}
