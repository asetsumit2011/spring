package com.springdemo.helloworld.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @RequestMapping
    public String helloWorld(){
        return "Hello World From Spring Boot Controller";
    }

    @RequestMapping("/goodbye")
    public String goodBye(){
        return "good bye from Spring boot";
    }

}
