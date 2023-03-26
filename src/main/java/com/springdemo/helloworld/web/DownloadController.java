package com.springdemo.helloworld.web;

import com.springdemo.helloworld.model.Photo;
import com.springdemo.helloworld.service.PhotosService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@RestController
public class DownloadController {

    private final PhotosService photosService;

    public DownloadController(PhotosService photosService) {
        this.photosService = photosService;
    }
    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable Integer id){

        final Photo photo = photosService.get(id);
        if(Objects.isNull(photo)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.valueOf(photo.getContentType()));
        ContentDisposition build = ContentDisposition
                .builder("inline")
                .filename(photo.getFileName())
                .build();
        httpHeaders.setContentDisposition(build);
        return new ResponseEntity<>(photo.getData(), httpHeaders, HttpStatus.OK);
    }
}
