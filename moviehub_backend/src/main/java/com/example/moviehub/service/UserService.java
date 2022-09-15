package com.example.moviehub.service;


import com.example.moviehub.collection.User;
import com.example.moviehub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    public User save(User user) {
        return userRepository.save(user);
    }
}
