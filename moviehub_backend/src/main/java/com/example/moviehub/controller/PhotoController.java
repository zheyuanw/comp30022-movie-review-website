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
import java.util.List;

@RestController
@RequestMapping("/photo")
public class PhotoController {
    @Autowired
    private PhotoServiceImpl photoServiceImpl;

    @PostMapping
    public String addPhoto(@RequestParam ("image") MultipartFile image) throws IOException {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        Photo photo = photoServiceImpl.getPhoto(userId);
        if (photo != null) {
            photoServiceImpl.deletePhoto(userId);
        }
        String id = photoServiceImpl.addPhoto(image.getOriginalFilename(), image, userId);

        return id;
    }

    @GetMapping("/userId={userId}")
    public ResponseEntity<Resource> downloadPhoto(@PathVariable String userId) {

        Photo photo = photoServiceImpl.getPhoto(userId);
        Resource resource
                = new ByteArrayResource(photo.getPhoto().getData());

        return  ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, photo.getId())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    /*@PostMapping("/edit")
    public String updatePhoto(@RequestBody PhotoForm form) throws IOException {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        photoServiceImpl.deletePhoto(userId);
        String pid = photoServiceImpl.addPhoto(form.getPhoto().getOriginalFilename(), form.getPhoto(), userId);
        return pid;
    }*/
}
