package com.example.moviehub.service;

import com.example.moviehub.collection.User;

public interface UserService {
    Boolean registerUser(User user);
    Boolean loginUser(User user);

}
