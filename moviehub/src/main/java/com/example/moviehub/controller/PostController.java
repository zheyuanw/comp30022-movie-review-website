package com.example.moviehub.controller;


import com.example.moviehub.collection.Post;
import com.example.moviehub.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public Post save (@RequestBody Post post){
        return postService.savePost(post);
    }

    @PostMapping("/updatePost")
    public Post updatePost(@RequestBody Post post){
        return postService.updatePost(post);
    }



}
