package com.example.moviehub.controller;


import com.example.moviehub.collection.RegisterForm;
import com.example.moviehub.collection.User;
import com.example.moviehub.service.RegisterServiceImpl;
import com.example.moviehub.service.UserServiceImpl;
import com.example.moviehub.util.JWTtokenUtil;
import lombok.RequiredArgsConstructor;
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
    public String verifyEmail(@RequestBody RegisterForm registerForm) {
        System.out.println(registerForm.getEmail());
        registerService.sendEmailVerificationCode(registerForm.getEmail());
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
            return JWTtokenUtil.generateToken(user).toString();
        }else{
            return "Login Failed";
        }

    }

}
