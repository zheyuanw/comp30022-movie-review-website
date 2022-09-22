package com.example.moviehub.controller;


import com.example.moviehub.collection.RegisterForm;
import com.example.moviehub.collection.User;
import com.example.moviehub.service.EmailServiceImpl;
import com.example.moviehub.service.RegisterServiceImpl;
import com.example.moviehub.service.UserServiceImpl;
import com.example.moviehub.util.JWTutil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final RegisterServiceImpl registerService;

    @PostMapping
    public String save(@RequestBody User user){
        return userService.save(user).getId();
    }

    @PutMapping
    public String update(@RequestBody User user){
        return userService.save(user).getId();
    }

    @PostMapping(value = "/email")
    public String verifyEmail(@RequestBody String email) {
        System.out.println(email);

        registerService.sendEmailVerificationCode(email);
        return "Email Sent";
    }

    @PostMapping(path = "/register")
    public String register(@RequestBody RegisterForm registerForm){
        if (registerService.register(registerForm)){
            return "Register succeeded";
        }else{
            return "Register Failed";
        }
    }

    @PostMapping(value = "/login")
    public String login(@RequestBody User user) {
        System.out.println(user);
        if (userService.loginUser(user)){
            return JWTutil.generateToken(user).toString();
        }else{
            return "Login Failed";
        }

    }

}
