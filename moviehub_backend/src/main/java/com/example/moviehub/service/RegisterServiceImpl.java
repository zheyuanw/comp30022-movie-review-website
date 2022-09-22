package com.example.moviehub.service;

import com.example.moviehub.collection.RegisterForm;
import com.example.moviehub.util.VerificationCodeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService{


    private final EmailServiceImpl emailService;
    private final UserServiceImpl userService;
    private final RedisServiceImpl redisService;

    @Override
    public void sendEmailVerificationCode(String email) {

        if (userService.getUserByEmail(email) != null){
            throw new RuntimeException("Email already registered");
        }

        String code = VerificationCodeUtil.generateCode();
        if (redisService.existKey(email)){
            redisService.updateString(email, code);
        }else{
            redisService.setString(email, code);
        }
        emailService.sendMail(email, "Verification Code", code);
    }


    @Override
    public boolean register(RegisterForm registerForm) {
        String email = registerForm.getEmail();
        String redis_code;

        //if there is a corresponding code in redis
        if (redisService.existKey(email)){
            redis_code = redisService.getString(email);

            //compared the code from client and server
            if (registerForm.getVerificationCode().equals(redis_code)){
                userService.registerUser(registerForm);
                return true;
            }
        }
        return false;
    }
}
