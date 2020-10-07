package com.nedupillil337.restservices.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nedupillil337.restservices.Entities.User;
import com.nedupillil337.restservices.Services.UserService;

//Controller 
@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	//getAll users Method
	@GetMapping("/users")
	public List<User>getAllUsers(){
		return userService.getAllUsers();
	}
	//Create User
	
    //@RequestBody Annotation
	//@PostMappingAnnotation
	@PostMapping("/users")
	public User createUser(@RequestBody User user)
	{
		return userService.createUser(user);
	}
	//getUser By Id
	@GetMapping("/users/{id}")
	public Optional<User> getUserById(@PathVariable("id") Long id){
		return userService.getUserById(id);
	}
	//UpdateUserBy Id
	@PutMapping("/users/{id}")
	public User updateUserById(@PathVariable("id") Long id,@RequestBody User user) {
	return userService.updateUserById(user,id);
	}
	
	//DeleteUserById
	@DeleteMapping("/users/{id}")
	public void deleteUserById(@PathVariable("id")Long id) {
		userService.deleteUserById(id);
	}
	//getUserByUserName
	@GetMapping("/users/byusername/{username}")
	public User getUserByUsername(@PathVariable("username") String username) {
		return userService.getUserByUsername(username);
	}
	}

