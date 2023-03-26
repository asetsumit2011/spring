package com.springdemo.helloworld.web;

import com.springdemo.helloworld.model.Photo;
import com.springdemo.helloworld.service.PhotosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

@RestController
public class HelloController {

    private final PhotosService photosService;

    public HelloController(PhotosService photosService) {
        this.photosService = photosService;
    }

    @RequestMapping("/hello")
    public String hello() {
        return "Hi Sumit Welcome to Spring Boot";
    }

    @GetMapping("/photos")
    public Iterable<Photo> getPhotos() {
        return photosService.get();
    }

    @GetMapping("/photos/{id}")
    public Photo getPhotos(@PathVariable final Integer id) {
        final Photo photo = photosService.get(id);
        if (Objects.isNull(photo)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return photo;
    }

    /*@DeleteMapping("/photos/{id}")
    public ResponseEntity delete(@PathVariable final Integer id) {
        photosService.remove(id);
        if (Objects.isNull(photo)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.OK).build();
    }*/

    @DeleteMapping("/photos/{id}")
    public void delete(@PathVariable final Integer id) {
        photosService.remove(id);
    }

    @PostMapping("/photos/")
    public Photo create(@RequestBody @Valid Photo photo) {

        photo.setId(UUID.randomUUID().toString());
        photosService.save(photo.getId(),photo);
        return photo;
    }

    @PostMapping("/createPhotos")
    public Photo createPhoto(@RequestPart("data") MultipartFile file) throws IOException {

        return photosService.put(file.getOriginalFilename(),file.getContentType(),file.getBytes());
    }
}
