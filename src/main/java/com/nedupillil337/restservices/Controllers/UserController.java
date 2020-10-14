package com.nedupillil337.restservices.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.nedupillil337.restservices.Entities.User;
import com.nedupillil337.restservices.Exceptions.UserExistsException;
import com.nedupillil337.restservices.Exceptions.UserNotFoundException;
import com.nedupillil337.restservices.Services.UserService;

//Controller 
@RestController
@RequestMapping(value="/users")
public class UserController {
	@Autowired
	private UserService userService;
	
	//getAll users Method
	@GetMapping
	public List<User>getAllUsers(){
		return userService.getAllUsers();
	}
	//Create User
	
    //@RequestBody Annotation
	//@PostMappingAnnotation
	@PostMapping
	public User createUser(@RequestBody User user,UriComponentsBuilder builder)
	{
		try {
		return userService.createUser(user);
		}catch (UserExistsException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage());
		}
		}
	
	//getUser By Id
	@GetMapping("/{id}")
	public Optional<User> getUserById(@PathVariable("id") Long id){
		
		try {
		return userService.getUserById(id);
		}catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
		}
	
	//UpdateUserBy Id
	@PutMapping("/{id}")
	public User updateUserById(@PathVariable("id") Long id,@RequestBody User user) {
		try {
		return userService.updateUserById(user,id);
		}catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage());
		}
	}
	
	//DeleteUserById
	@DeleteMapping("/{id}")
	public void deleteUserById(@PathVariable("id")Long id) {
		userService.deleteUserById(id);
	}
	//getUserByUserName
	@GetMapping("/byusername/{username}")
	public User getUserByUsername(@PathVariable("username") String username) {
		return userService.getUserByUsername(username);
	}
	}

