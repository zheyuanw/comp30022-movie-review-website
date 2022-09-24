package com.example.moviehub.service.Impl;


import com.example.moviehub.collection.RegisterForm;
import com.example.moviehub.collection.User;
import com.example.moviehub.repository.UserRepository;
import com.example.moviehub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    UserRepository userRepository;
    public User save(User user) {
        return userRepository.save(user);
    }

    public User getUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }

    @Override
    public Boolean registerUser(RegisterForm registerForm) {
        System.out.println(registerForm);

        if (userRepository.findUserByEmail(registerForm.getEmail())!=null){
            System.out.println("Found duplicate");
            return Boolean.FALSE;
        }else{
            System.out.println("Inser user:" + registerForm.getEmail());

            userRepository.insert(new User(registerForm.getEmail(), registerForm.getPassword()));
            return Boolean.TRUE;
        }
    }

    @Override
    public Boolean loginUser(User user) {
        User temp = userRepository.findUserByEmail(user.getEmail());
        if (temp==null){
            String error_msg = "Wrong email";
            System.out.println(error_msg);
            return false;
        }else{
            if (temp.getPassword().equals(user.getPassword())){
                System.out.println("Login Success");
                return true;
            }else{
                System.out.println("Wrong password");
                return false;
            }
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = getUserByEmail(email);
        if (user == null){
            throw new UsernameNotFoundException("User not found with email: " + email);
        }else {
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
        }
    }
}
