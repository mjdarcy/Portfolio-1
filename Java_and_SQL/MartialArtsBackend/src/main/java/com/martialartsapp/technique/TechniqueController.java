package com.martialartsapp.technique;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.martialartsapp.user.TechniqueResponse;
import com.martialartsapp.user.User;
import com.martialartsapp.user.UserRepository;

@RestController
@RequestMapping(path="/api/technique")
public class TechniqueController 
{
	
	@Autowired
	private TechniqueRepository techRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@PostMapping
	ResponseEntity<List<Technique>> addTechnique(
			@RequestParam String creator,
			@RequestParam String name, 
			@RequestParam String type, 
			@RequestParam String description, 
			@RequestParam MultipartFile video
			)
	{
		byte[] convertedVid = null; try { convertedVid = video.getBytes(); } catch (IOException e) { e.printStackTrace(); }
		
		User user = userRepo.findById(creator).orElse(null);
		user.getTechs().add(new Technique(creator, name, type, description, convertedVid));
		return ResponseEntity.ok(userRepo.save(user).getTechs());
	}
	
	
	@DeleteMapping(path="/{id}")
	ResponseEntity<Technique> deleteTechnique(@PathVariable(value="id") Integer id)
	{
		Technique deleted = techRepo.findById(id).orElse(null);
		techRepo.deleteById(id);
		return ResponseEntity.ok(deleted);
	}
	
	@GetMapping(path="/all")
	List<Technique> getAllTechniques() { return techRepo.findAll(); }
	
	@GetMapping(path="/name/{name}")
	List<Technique> getTechniqueByName(@PathVariable(value="name") String name) { return techRepo.findByName(name); }
	
	@GetMapping(path="/type/{type}")
	List<Technique> getTechniqueByType(@PathVariable(value="type") String type)
	{ return techRepo.findByType(type); }
	
	@GetMapping(path="/types")
	List<String> getTechniqueTypes() { return techRepo.findDistinctTypes(); }
	
	@GetMapping(path="/video/{id}", produces = "video/mp4")
	byte[] getVideo(@PathVariable Integer id)
	{
		byte[] video = techRepo.findById(id)
				.orElse(null)
				.getVideo();
		return video;
	}
	
	@GetMapping(path="/fav/{username}")
	List<List<Technique>> getUserFavorites(@PathVariable(value="username") String user) {
		
		Set<String> covered = new HashSet<String>();
		
		List<Technique> userFavs = userRepo.findById(user).get().getFavorites();
		
		List<List<Technique>> result = new LinkedList<List<Technique>>();
		int start = 0;
		for(int i = 0; i < userFavs.size(); i++)
		{
			if(covered.add(userFavs.get(i).getType()))
			{
				List<Technique> subList = new LinkedList<Technique>(userFavs.subList(start, i));
				result.add(subList);
				start = i;
			}
		}
		return result;
	}
	
	
	@GetMapping(path="/popular")
	List<Technique> getTop()
	{
		List<TechniqueResponse> full = userRepo.findPopular();
		Set<String> popular = new HashSet<String>();
		List<Technique> response = new ArrayList<Technique>();
		for(TechniqueResponse t : full) if(popular.add(t.getName()) && t.getN() > 1) response.add(new Technique(t.getId(), t.getCreator(), t.getName(), t.getType(), t.getDescription(), t.getVideo()));
		return response;
		
	}
	
	@GetMapping(path="latest")
	List<Technique> getLatest() { return techRepo.findLatest(); }
}
