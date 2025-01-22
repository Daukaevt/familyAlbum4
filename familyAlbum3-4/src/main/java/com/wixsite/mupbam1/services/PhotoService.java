package com.wixsite.mupbam1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.wixsite.mupbam1.models.Pics;
import com.wixsite.mupbam1.repository.PhotoRepository;

import java.util.List;

@Service
public class PhotoService {
	@Autowired
	private PhotoRepository photoRepository;

	// Создание нового фото
	public Pics savePhoto(Pics pic) {
		return photoRepository.save(pic);
	}

	// Получение всех фото
	public List<Pics> getAllPhotos() {
		return photoRepository.findAll();
	}

	// Получение всех фото уникального пользователя
	public List<Pics> getAlbumByUserKey(String username) {
		return photoRepository.findAll().stream().filter(s -> s.getOwner_key().equals(username)).toList();
	}

	// Получение фото по ID
	public Pics getPhotoById(Long id) {
		return photoRepository.findById(id).orElse(null);
	}

	// Удаление фото
	public void deleteUser(Long id) {
		photoRepository.deleteById(id);
	}

	// Получение уникального имени пользователя
	public String getOwner(OAuth2User oauth2User) {
		String uniqueUser = null;
		if (googleUser(oauth2User)) {
			uniqueUser = oauth2User.getAttribute("email");
		} else if (githubUser(oauth2User)) {
			uniqueUser = "https://github.com/".concat(oauth2User.getAttribute("login"));
		} else {
			return "unknown_owner";
		}
		return uniqueUser;
	}

	public boolean githubUser(OAuth2User oauth2User) {
		return oauth2User.getAttribute("id") != null;
	}

	public boolean googleUser(OAuth2User oauth2User) {
		return oauth2User.getAttribute("sub") != null;
	}

}
