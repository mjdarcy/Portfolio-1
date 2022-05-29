package com.martialartsapp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.martialartsapp.technique.Technique;
import com.martialartsapp.technique.TechniqueRepository;

@RestController
@RequestMapping(path="/api/user")
public class UserController {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private TechniqueRepository techRepo;
	
	@PostMapping
	ResponseEntity<User> addUser(@RequestBody User user)
	{
		return ResponseEntity.ok(userRepo.save(user));
	}
	
	@PostMapping(path="/favorites/{username}/{id}")
	ResponseEntity<User> addFavorite(@PathVariable String username, @PathVariable Integer id)
	{
		Technique foundTech = techRepo.findById(id).orElse(null);
		User foundUser = userRepo.findById(username).orElse(null);
		foundUser.getFavorites().add(foundTech);
		return ResponseEntity.ok(userRepo.save(foundUser));
	}
	
	@GetMapping(path="/{username}/{password}")
	User getUser(@PathVariable String username, @PathVariable String password) { return userRepo.findByUsernameAndPassword(username, password); }
}