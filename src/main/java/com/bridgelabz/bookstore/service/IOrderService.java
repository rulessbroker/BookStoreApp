package com.bridgelabz.bookstore.service;

import java.util.List;

import com.bridgelabz.bookstore.dto.OrderDTO;
import com.bridgelabz.bookstore.model.OrderModel;

public interface IOrderService {
	public OrderModel insertOrder(OrderDTO orderdto);

	public List<OrderModel> getAllOrderRecords();

	public OrderModel getOrderRecord(Integer id);

	public OrderModel updateOrderRecord(Integer id, OrderDTO dto);

	public OrderModel deleteOrderRecord(Integer id);

}
