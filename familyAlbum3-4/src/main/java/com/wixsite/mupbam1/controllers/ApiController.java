package com.wixsite.mupbam1.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wixsite.mupbam1.dto.PhotoDto;
import com.wixsite.mupbam1.dto.ShapeDto;
import com.wixsite.mupbam1.models.Pics;
import com.wixsite.mupbam1.services.PhotoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ApiController {
	@Autowired
	private PhotoService photoService;
	//private OAuth2UserUtil oauth2User;

	// Эндпоинт для получения списка фотографий
	@GetMapping("/photos")
	public List<PhotoDto> getPhotos() {
		return List.of(new PhotoDto(1L, "/images/photo1.jpg", "Beautiful Landscape"),
				new PhotoDto(2L, "/images/photo2.jpg", "City Skyline"),
				new PhotoDto(3L, "/images/photo3.jpg", "Sunny Beach"));
	}

	// Эндпоинт для получения списка фигур
	@GetMapping("/shapes")
	public List<ShapeDto> getShapes() {
		return List.of(new ShapeDto("circle", 50, "#FF5733"), new ShapeDto("square", 100, "#33FF57"),
				new ShapeDto("triangle", 70, "#3357FF"));
	}

//сохранения фотографии
	@PostMapping("/photos/add")
	@ResponseBody
	public ResponseEntity<String> addPhoto(@RequestBody Map<String, String> photoData, 
			Authentication authentication) {
		
		String url = photoData.get("url");
		String description = photoData.get("description");

		Pics newPhoto = new Pics();
		if (authentication.getPrincipal() instanceof OAuth2User oauth2User) {
        newPhoto.setDescription(description);
        newPhoto.setUrl(url);
        newPhoto.setOwner_key(photoService.getOwner(oauth2User));
        photoService.savePhoto(newPhoto);
        System.out.println(newPhoto.toString());
        
		}

		return ResponseEntity.ok("Photo added successfully");
	}
}