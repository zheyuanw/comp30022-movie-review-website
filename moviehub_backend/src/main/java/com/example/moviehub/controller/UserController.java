package com.example.moviehub.controller;


import com.example.moviehub.collection.User;
import com.example.moviehub.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @PostMapping
    public String save(@RequestBody User user){
        return userService.save(user).getId();
    }

    @PutMapping
    public String update(@RequestBody User user){
        return userService.save(user).getId();
    }


    @PostMapping(path = "/register")
    public String register(@RequestBody User user){
        if (userService.registerUser(user)){
            return "Register succeeded";
        }else{
            return "Register Failed";
        }

    }

}
