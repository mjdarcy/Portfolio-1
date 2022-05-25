package com.martialartsapp.technique;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TechniqueRepository extends JpaRepository<Technique, Integer>
{ 
	List<Technique> findByName(String name);
	List<Technique> findByType(String type);
	@Query("SELECT DISTINCT type FROM Technique")
	List<String> findDistinctTypes();
	/*@Query("SELECT new com.martialartsapp.technique.TechniqueResponse(t.id, t.creator, t.name, t.type, t.description, t.video) FROM Technique t JOIN t.techs f WHERE f.username = :username ORDER BY t.type, t.name")
	@Query("FROM User u JOIN Technique t ")
	List<TechniqueResponse> findUserFavs(String username);
	@Query("SELECT new com.martialartsapp.technique.TechniqueResponse(t.id, t.creator, t.name, t.type, t.description, t.video, COUNT(f.techId) AS fav_count) FROM Technique t JOIN t.techs f GROUP BY t.id ORDER BY t.name, fav_count DESC")
	List<TechniqueResponse> findPopular();*/
	@Query(value = "SELECT id, creator, name, type, description, video FROM Technique ORDER BY id DESC LIMIT 10", nativeQuery = true)
	List<Technique> findLatest();
	//String creator, String name, String type, String description, MultipartFile video
}