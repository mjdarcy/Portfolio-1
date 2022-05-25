package com.martialartsapp.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String>
{
	User findByUsernameAndPassword(String username, String password);
}