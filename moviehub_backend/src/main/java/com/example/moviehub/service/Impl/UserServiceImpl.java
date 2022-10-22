package com.example.moviehub.service.Impl;


import com.example.moviehub.collection.form.ForgotPasswrodForm;
import com.example.moviehub.collection.form.RegisterForm;
import com.example.moviehub.collection.User;
import com.example.moviehub.repository.UserRepository;
import com.example.moviehub.service.UserService;
import com.example.moviehub.util.VerificationCodeUtil;
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


    private final RedisServiceImpl redisService;

    private final EmailServiceImpl emailService;

    public UserServiceImpl(RedisServiceImpl redisService, EmailServiceImpl emailService) {
        this.redisService = redisService;
        this.emailService = emailService;
    }

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

            User.Gender gender;

            if (registerForm.getGender().equalsIgnoreCase("male")){
                gender = User.Gender.MALE;
            }else if (registerForm.getGender().equalsIgnoreCase("female")){
                gender = User.Gender.FEMALE;
            }else {
                return Boolean.FALSE;
            }

            userRepository.insert(new User(registerForm.getUsername(),
                    registerForm.getEmail(),
                    registerForm.getPassword(),
                    registerForm.getAge(),
                    gender));
            return Boolean.TRUE;
        }
    }

    public Boolean changePass(User user){
        User temp_user = userRepository.findUserByEmail(user.getEmail());
        if (temp_user != null){
            temp_user.setPassword(user.getPassword());
            userRepository.save(temp_user);
            return Boolean.TRUE;
        }else {
            return Boolean.FALSE;
        }

    }

    public Boolean forgotPass(ForgotPasswrodForm form){
        if (redisService.verifyCode(form.getEmail(), form.getVerificationCode())){
            User user = userRepository.findUserByEmail(form.getEmail());
            user.setPassword(form.getPassword());
            userRepository.save(user);
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    public void sendEmailVerificationCode(String email) {

        if (userRepository.findUserByEmail(email) == null){
            throw new RuntimeException("Email not registered");
        }

        String code = VerificationCodeUtil.generateCode();
        if (redisService.existKey(email)){
            System.out.println("update");
            System.out.println(redisService.getString(email).toString());
            redisService.updateString(email, code);
        }else{
            System.out.println("set");
            redisService.setString(email, code);
        }
        System.out.println(code);
        emailService.sendHtmlMail(email, "Verification Code for resetting password", "Resetting Password", code);
//        emailService.sendMail(email, "Verification Code for resetting password",  code);
    }

    @Override
    public String loginUser(User user) {
        User temp = userRepository.findUserByEmail(user.getEmail());
        if (temp==null){
            String error_msg = "Wrong email";
            System.out.println(error_msg);
            return null;
        }else{
            if (temp.getPassword().equals(user.getPassword())){
                System.out.println("Login Success");
                return temp.getId();
            }else{
                System.out.println("Wrong password");
                return null;
            }
        }
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
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
