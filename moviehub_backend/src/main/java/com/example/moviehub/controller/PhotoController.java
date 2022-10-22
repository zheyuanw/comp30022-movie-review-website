package com.example.moviehub.controller;



import com.example.moviehub.collection.Photo;
import com.example.moviehub.collection.form.PhotoForm;
import com.example.moviehub.service.Impl.PhotoServiceImpl;
import com.example.moviehub.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@RestController
@RequestMapping("/photo")
public class PhotoController {
    @Autowired
    private PhotoServiceImpl photoServiceImpl;

    @PostMapping
    public String addPhoto(@RequestBody PhotoForm form) throws IOException {
        String userid = SecurityContextHolder.getContext().getAuthentication().getName();
        String id = photoServiceImpl.addPhoto(form.getAvatar().getOriginalFilename(), form.getAvatar(), userid);
        return  id;
    }

    @GetMapping
    public ResponseEntity<Resource> downloadPhoto() {
        String userid = SecurityContextHolder.getContext().getAuthentication().getName();

        Photo photo = photoServiceImpl.getPhoto(userid);
        Resource resource
                = new ByteArrayResource(photo.getPhoto().getData());

        return  ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, photo.getId())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @PostMapping("/edit")
    public String updatePhoto(@RequestBody PhotoForm form) throws IOException {
        String userid = SecurityContextHolder.getContext().getAuthentication().getName();
        photoServiceImpl.deletePhoto(userid);
        String pid = photoServiceImpl.addPhoto(form.getAvatar().getOriginalFilename(), form.getAvatar(), userid);
        return pid;
    }
}
