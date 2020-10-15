package com.nedupillil337.restservices.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.io.Resources;
import com.nedupillil337.restservices.Entities.Order;
import com.nedupillil337.restservices.Entities.User;
import com.nedupillil337.restservices.Exceptions.UserNotFoundException;
import com.nedupillil337.restservices.Repositories.OrderRepository;
import com.nedupillil337.restservices.Repositories.UserRepository;

@RestController
@RequestMapping(value = "/hateoas/users")
public class OrderHateoasController {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	// get All Orders for a user

	@GetMapping("/{userid}/orders")
	public Resources<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException {

		Optional<User> userOptional = userRepository.findById(userid);
		if (!userOptional.isPresent())
			throw new UserNotFoundException("User Not Found");

		List<Order> allorders =  userOptional.get().getOrders();
		Resources<Order> finalResources = new Resources<Order>(allorders);
		
		return finalResources;
	}
}
