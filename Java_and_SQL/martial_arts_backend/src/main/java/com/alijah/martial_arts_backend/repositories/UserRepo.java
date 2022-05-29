package com.alijah.martial_arts_backend.repositories;

import org.springframework.data.repository.CrudRepository;

import com.alijah.martial_arts_backend.models.User;

public interface UserRepo extends CrudRepository<User, String>
{

	User findByUsernameAndPassword(String username, String password);
	
}