package com.springdemo.helloworld.repository;

import com.springdemo.helloworld.model.Photo;
import org.springframework.data.repository.CrudRepository;

public interface PhotosRepository extends CrudRepository<Photo, Integer> {
}
