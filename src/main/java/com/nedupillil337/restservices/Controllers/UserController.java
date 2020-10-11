package com.nedupillil337.restservices.Controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.nedupillil337.restservices.Entities.User;
import com.nedupillil337.restservices.Exceptions.UserExistsException;
import com.nedupillil337.restservices.Exceptions.UserNameNotFoundException;
import com.nedupillil337.restservices.Exceptions.UserNotFoundException;
import com.nedupillil337.restservices.Services.UserService;

//Controller 
@RestController
@Validated
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
	public ResponseEntity<Void> createUser(@Valid @RequestBody User user, UriComponentsBuilder builder) {
		try {
			userService.createUser(user);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
			
		} catch(UserExistsException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
	}
	
	//getUser By Id
	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable("id") @Min(1) Long id){
		
		try {
			Optional<User> userOptional =  userService.getUserById(id);
			return userOptional.get();
		}catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
		}
	
	//UpdateUserBy Id
	@PutMapping("/users/{id}")
	public User updateUserById(@PathVariable("id") Long id,@RequestBody User user) {
		try {
		return userService.updateUserById(user,id);
		}catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage());
		}
	}
	
	//DeleteUserById
	@DeleteMapping("/users/{id}")
	public void deleteUserById(@PathVariable("id")Long id) {
		userService.deleteUserById(id);
	}
	//getUserByUserName
	@GetMapping("/users/byusername/{username}")
	public User getUserByUsername(@PathVariable("username") String username)throws UserNameNotFoundException {
		User user =  userService.getUserByUsername(username);
		if(user == null)
			throw new UserNameNotFoundException("Username: '" + username + "' not found in User repository");
		return user;
	}
	}

