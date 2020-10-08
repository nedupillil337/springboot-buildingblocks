package com.nedupillil337.restservices.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.nedupillil337.restservices.Entities.User;
import com.nedupillil337.restservices.Exceptions.UserExistsException;
import com.nedupillil337.restservices.Exceptions.UserNotFoundException;
import com.nedupillil337.restservices.Repositories.UserRepository;

//Service

@Service
public class UserService {
@Autowired
	private UserRepository userRepository;
//get all users method

	  
  
public List<User> getAllUsers() {
	return userRepository.findAll();
}
//Create User

public User createUser(User user) throws UserExistsException{
	
	//if user exists using username
	User existingUser=userRepository.findByUsername(user.getUsername());
	if(existingUser!=null)
	{
		throw new UserExistsException("User already Exists");
	}
	
	//if exists throw userexists exception
	
	return userRepository.save(user);
	}
//get User by Id
public Optional<User> getUserById(Long id)throws UserNotFoundException{
	Optional<User> user=userRepository.findById(id);
	
	if(!user.isPresent()) {
		throw new UserNotFoundException("User not Found in the Repository");
	}
	
	return user;
	
}
//updateUserById
public User updateUserById(User user,Long id)throws UserNotFoundException {
	
Optional<User> OptionalUser=userRepository.findById(id);
	
	if(!OptionalUser.isPresent()) {
		throw new UserNotFoundException("User not Found in the Repository,Provide the correct User Id");
	}
	
	user.setId(id);
	return userRepository.save(user);
}
//Delete User By ID
 public void deleteUserById(Long id) {
	 Optional<User> OptionalUser=userRepository.findById(id);
		
		if(!OptionalUser.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User not Found in the Repository,Provide the correct User Id");
		}
		 
	 
	 userRepository.deleteById(id);
 }
 //getUserByUserName
 public User getUserByUsername(String username) {
	  return userRepository.findByUsername(username);
 }
}

