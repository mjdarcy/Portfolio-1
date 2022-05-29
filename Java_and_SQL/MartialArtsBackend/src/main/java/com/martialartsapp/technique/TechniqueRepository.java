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
	@Query(value = "SELECT id, creator, name, type, description, video FROM Technique ORDER BY id DESC LIMIT 10", nativeQuery = true)
	List<Technique> findLatest();
}