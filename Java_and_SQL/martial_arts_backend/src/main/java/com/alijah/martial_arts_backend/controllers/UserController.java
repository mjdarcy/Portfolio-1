package com.alijah.martial_arts_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alijah.martial_arts_backend.models.User;
import com.alijah.martial_arts_backend.repositories.UserRepo;

@RestController
@RequestMapping(path="/api/user")
public class UserController {

	@Autowired
	private UserRepo repo;
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<User> postUser(@RequestBody User user)
	{
		System.out.println("in method");
		return ResponseEntity.ok(repo.save(user));
	}
	
	@GetMapping(path="/{username}/{password}")
	User getUser(@PathVariable String username, @PathVariable String password) { return repo.findByUsernameAndPassword(username, password); }
}


/*
@PostMapping
ResponseEntity<User> addUser(@RequestBody User user)
{
	return ResponseEntity.ok(userRepo.save(user));
}
*/