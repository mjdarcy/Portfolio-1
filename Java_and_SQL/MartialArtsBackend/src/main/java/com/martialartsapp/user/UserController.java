package com.martialartsapp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(path="/api/user")
public class UserController {

	@Autowired UserRepository userRepo;
	
	@PostMapping
	ResponseEntity<User> postUser(@RequestBody User user) { return ResponseEntity.ok(userRepo.save(user)); }
	
	@GetMapping("/{username}/{password}")
	User getUser(@PathVariable String username, @PathVariable String password) { return userRepo.findByUsernameAndPassword(username, password); }
}