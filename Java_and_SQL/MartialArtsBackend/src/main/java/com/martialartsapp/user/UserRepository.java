package com.martialartsapp.user;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String>
{
	User findByUsernameAndPassword(String username, String password);
	@Query("SELECT new com.martialartsapp.user.TechniqueResponse(f.id, f.creator, f.name, f.type, f.description, f.video, COUNT(f.id) AS fav_count) FROM User u JOIN u.favorites f GROUP BY f.id ORDER BY f.name, fav_count DESC")
	List<TechniqueResponse> findPopular();
	
}