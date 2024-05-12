package com.everlearn.everlearnwebapp.service;

import com.everlearn.everlearnwebapp.entity.Photo;
import com.everlearn.everlearnwebapp.entity.User;
import com.everlearn.everlearnwebapp.repository.PhotoRespository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoService {

    @Autowired
    private UserService userService;
    @Autowired
    private PhotoRespository photoRespository;

    @Transactional
    public void addPhoto(Long id, byte[] data) {
        User user = userService.findById(id);
        Photo photo = new Photo(user, data);
        photoRespository.save(photo);
    }

    @Transactional
    public byte[] getPhoto(Long id) {
        User user = userService.findById(id);
        Photo photo = photoRespository.findByUser(user);
        return photo.getData();
    }
}
