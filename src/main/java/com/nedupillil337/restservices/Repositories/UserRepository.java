package com.nedupillil337.restservices.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nedupillil337.restservices.Entities.User;
//Repository
@Repository

public interface UserRepository extends JpaRepository<User,Long>{
	User findByUsername(String username);

}
