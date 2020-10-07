package com.nedupillil337.restservices.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nedupillil337.restservices.Entities.User;
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

public User createUser(User user){
	return userRepository.save(user);
	}
//get User by Id
public Optional<User> getUserById(Long id) {
	Optional<User> user=userRepository.findById(id);
	return user;
}
//updateUserById
public User updateUserById(User user,Long id) {
	user.setId(id);
	return userRepository.save(user);
}
//Delete User By ID
 public void deleteUserById(Long id) {
	 if(userRepository.findById(id).isPresent()) {
		 userRepository.deleteById(id);
	 }
 }
 //getUserByUserName
 public User getUserByUsername(String username) {
	  return userRepository.findByUsername(username);
 }
}

