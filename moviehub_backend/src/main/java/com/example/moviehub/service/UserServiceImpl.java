package com.example.moviehub.service;


import com.example.moviehub.collection.User;
import com.example.moviehub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;
    public User save(User user) {
        return userRepository.save(user);
    }

    public Boolean registerUser(User user) {
        System.out.println(user);

        if (userRepository.findUserByEmail(user.getEmail())!=null){
            System.out.println("Found duplicate");
            return Boolean.FALSE;
        }else{
            System.out.println("Inser user:" + user.toString());
            userRepository.insert(user);
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
}
