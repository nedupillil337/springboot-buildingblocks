package com.nedupillil337.restservices.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nedupillil337.restservices.Entities.Order;
import com.nedupillil337.restservices.Entities.User;
import com.nedupillil337.restservices.Exceptions.UserNotFoundException;
import com.nedupillil337.restservices.Repositories.OrderRepository;
import com.nedupillil337.restservices.Repositories.UserRepository;


@RestController
@RequestMapping(value = "/users")
public class OrderController {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	// get All Orders for a user

	@GetMapping("/{userid}/orders")
	public List<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException {

		Optional<User> userOptional = userRepository.findById(userid);
		if (!userOptional.isPresent())
			throw new UserNotFoundException("User Not Found");

		return userOptional.get().getOrders();
	}
	// Create Order

		@PostMapping("{userid}/orders")
		public Order createOrder(@PathVariable Long userid, @RequestBody Order order) throws UserNotFoundException {
			Optional<User> userOptional = userRepository.findById(userid);

			if (!userOptional.isPresent())
				throw new UserNotFoundException("User Not Found");

			User user = userOptional.get();
			order.setUser(user);
			return orderRepository.save(order);

		}
		
		//Get Order By OrderId
		
		@GetMapping("{userid}/orders/{orderid}")
		public List<Order> getOrderByOrderId(@PathVariable Long userid,Long orderid) throws UserNotFoundException {

			Optional<User> userOptional = userRepository.findById(orderid);
			if (!userOptional.isPresent())
				throw new UserNotFoundException("User Not Found");

			return userOptional.get().getOrders();
		}
}
