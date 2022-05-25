package com.martialartsapp.message;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer>{

	List<Message> findByUsername(String username);
	List<Message> findByRecipient(String username);
}
