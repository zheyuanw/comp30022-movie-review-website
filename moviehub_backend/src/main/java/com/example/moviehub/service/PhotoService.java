package com.example.moviehub.service;

import com.example.moviehub.collection.Photo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PhotoService {


    public String addPhoto(String Filename, MultipartFile image,String userId) throws IOException;

    public Photo getPhoto(String userId);

    public void deletePhoto(String userId);

}
