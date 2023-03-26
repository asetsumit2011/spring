package com.springdemo.helloworld.service;

import com.springdemo.helloworld.model.Photo;
import com.springdemo.helloworld.repository.PhotosRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Service
public class PhotosService {

    //this way of initialize map is called as instance initializer
    /*private Map<String, Photo> db = new HashMap<>() {{
        put("1", new Photo("1", "hello.jpg"));
    }};

    public Collection<Photo> get(){
        return db.values();
    }

    public Photo remove(String id) {
        return db.remove(id);
    }

    public void save(String id, Photo photo) {
        db.put(id,photo);
    }

    public Photo get(String id) {
        return db.get(id);
    }

    public Photo put(String fileName, String contentType, byte[] data) {
        final Photo photo = new Photo();
        photo.setId(UUID.randomUUID().toString());
        photo.setContentType(contentType);
        photo.setFileName(fileName);
        photo.setData(data);
        db.put(photo.getId(),photo);
        return photo;
    }*/

    private PhotosRepository photosRepository;

    public PhotosService(PhotosRepository photosRepository) {
        this.photosRepository = photosRepository;
    }


    public Iterable<Photo> get(){
        return photosRepository.findAll();
    }

    public void remove(Integer id) {
        photosRepository.deleteById(id);
    }

    public void save(String id, Photo photo) {
        photosRepository.save(photo);
    }

    public Photo get(Integer id) {
        return photosRepository.findById(id).orElse(null);
    }

    public Photo put(String fileName, String contentType, byte[] data) {
        final Photo photo = new Photo();

        photo.setContentType(contentType);
        photo.setFileName(fileName);
        photo.setData(data);
        photosRepository.save(photo);
        return photo;
    }
}
