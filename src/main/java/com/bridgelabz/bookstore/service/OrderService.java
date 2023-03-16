package com.bridgelabz.bookstore.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.dto.CartDTO;
import com.bridgelabz.bookstore.dto.OrderDTO;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.model.BookModel;
import com.bridgelabz.bookstore.model.CartModel;
import com.bridgelabz.bookstore.model.OrderModel;
import com.bridgelabz.bookstore.model.UserModel;
import com.bridgelabz.bookstore.repository.BookRepository;
import com.bridgelabz.bookstore.repository.CartRepository;
import com.bridgelabz.bookstore.repository.OrderRepository;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.util.EmailService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderService implements IOrderService {
	// Autowired to inject dependency here
	@Autowired
	private OrderRepository orderRepo;
	@Autowired
	private BookRepository bookRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private CartRepository cartRepo;

	@Autowired
	EmailService mailService;

	// Ability to serve controller's insert order record api call
	public OrderModel insertOrder(OrderDTO orderdto) {
		Optional<BookModel> book = bookRepo.findById(orderdto.getBookID());
		Optional<UserModel> user = userRepo.findById(orderdto.getUserID());
		if (book.isPresent() && user.isPresent()) {
			Random rand = new Random();
			int orderID = rand.nextInt(100000);
			if (orderdto.getQuantity() <= book.get().getQuantity()) {
				OrderModel newOrder = new OrderModel(orderID, orderdto.getQuantity(), orderdto.getAddress(), book.get(),
						user.get(), orderdto.isCancel());
				orderRepo.save(newOrder);
				if(orderdto.isCancel() == false) {
					cartRepo.deleteById(orderdto.getCartID());
				}
				book.get().setQuantity(book.get().getQuantity() - orderdto.getQuantity());
				bookRepo.save(book.get());
				log.info("Order record inserted successfully");
				mailService.sendEmail(user.get().getEmail(), "Your Order Placed successfully",
						"Hello " + user.get().getEmail() + ", Your order ID is " + orderID
								+ " & it is placed successfully on " + newOrder.getDate()
								+ " and will be delivered to you shortly.");
				return newOrder;
			} else {
				throw new BookStoreException("Requested quantity is not available");
			}
		} else {
			throw new BookStoreException("Book or User doesn't exists");
		}
	}

	// Ability to serve controller's retrieve all order records api call
	public List<OrderModel> getAllOrderRecords() {
		List<OrderModel> orderList = orderRepo.findAll();
		log.info("ALL order records retrieved successfully");
		return orderList;
	}

	// Ability to serve controller's retrieve order record by id api call
	public OrderModel getOrderRecord(Integer id) {
		Optional<OrderModel> order = orderRepo.findById(id);
		if (order.isEmpty()) {
			throw new BookStoreException("Order Record doesn't exists");
		} else {
			log.info("Order record retrieved successfully for id " + id);
			return order.get();
		}
	}

	// Ability to serve controller's update order record by id api call
	public OrderModel updateOrderRecord(Integer id, OrderDTO dto) {
		Optional<OrderModel> order = orderRepo.findById(id);
		Optional<BookModel> book = bookRepo.findById(dto.getBookID());
		Optional<UserModel> user = userRepo.findById(dto.getUserID());
		if (order.isEmpty()) {
			throw new BookStoreException("Order Record doesn't exists");
		} else {
			if (book.isPresent() && user.isPresent()) {
				if (dto.getQuantity() <= book.get().getQuantity()) {
					OrderModel newOrder = new OrderModel(id, dto.getQuantity(), dto.getAddress(), book.get(),
							user.get(), dto.isCancel());
					orderRepo.save(newOrder);
					log.info("Order record updated successfully for id " + id);
					book.get().setQuantity(book.get().getQuantity() - (dto.getQuantity() - order.get().getQuantity()));
					bookRepo.save(book.get());
					return newOrder;
				} else {
					throw new BookStoreException("Requested quantity is not available");
				}
			} else {
				throw new BookStoreException("Book or User doesn't exists");

			}
		}
	}

	// Ability to serve controller's delete order record by id api call
	public OrderModel deleteOrderRecord(Integer id) {
		Optional<OrderModel> order = orderRepo.findById(id);
		Optional<BookModel> book = bookRepo.findById(order.get().getBook().getBookId());
		if (order.isEmpty()) {
			throw new BookStoreException("Order Record doesn't exists");
		} else {
			book.get().setQuantity(book.get().getQuantity() + order.get().getQuantity());
			bookRepo.save(book.get());
			orderRepo.deleteById(id);
			log.info("Order record deleted successfully for id " + id);
			return order.get();
		}
	}
}
