package com.martialartsapp.message;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.martialartsapp.user.UserRepository;

@RestController
@RequestMapping(path="/api/message")
public class MessageController 
{
	
	@Autowired
	private MessageRepository msgRepo;
	
	@GetMapping(path="/sent/{username}")
	List<Message> getSentMessages(@PathVariable String username) { return msgRepo.findByUsername(username); }
	
	@GetMapping(path="/received/{username}")
	List<Message> getReceivedMessages(@PathVariable String username) { return msgRepo.findByRecipient(username); }
	
	@PostMapping
	ResponseEntity<Message> addMessage(@RequestBody Message m)
	{
		m.setDatePosted(new Date(System.currentTimeMillis()));
		return ResponseEntity.ok(msgRepo.save(m));
	}
}