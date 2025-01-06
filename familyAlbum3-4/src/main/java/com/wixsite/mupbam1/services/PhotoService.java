package com.wixsite.mupbam1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wixsite.mupbam1.models.Pics;
import com.wixsite.mupbam1.repository.PhotoRepository;

import java.util.List;

@Service
public class PhotoService {
    @Autowired
    private PhotoRepository photoRepository;

    // Создание нового пользователя
    public Pics savePhoto(Pics pic) {
        return photoRepository.save(pic);
    }

    // Получение всех пользователей
    public List<Pics> getAllPhotos() {
        return photoRepository.findAll();
    }

    // Получение пользователя по ID
    public Pics getPhotoById(Long id) {
        return photoRepository.findById(id).orElse(null);
    }

    // Удаление пользователя
    public void deleteUser(Long id) {
        photoRepository.deleteById(id);
    }
}

