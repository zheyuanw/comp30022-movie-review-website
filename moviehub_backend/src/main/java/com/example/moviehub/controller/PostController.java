package com.example.moviehub.controller;


import com.example.moviehub.collection.Post;
import com.example.moviehub.service.PostService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public Post save (@RequestBody Post post){
        return postService.savePost(post);
    }

    @PutMapping
    public Post updatePost(@RequestBody Post post){
        return postService.updatePost(post);
    }

    @GetMapping
    public List<Post> getAllPost(){
        return postService.getAllPost();
    }

    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable String id){
        postService.deletePost(id);
        return id;
    }

    @GetMapping("/{userid}")
    public List<Post> getPostByUserId(@PathVariable String userId){
        return postService.getPostByUserId(userId);
    }

    @GetMapping("/getPostByName")
    public List<Post> getPostByName(@RequestParam String movieName){
        return postService.getPostByName(movieName);
    }

    @GetMapping("/getAvgRatingByName")
    public List<Document> getAvgRating(@RequestParam String movieName){
        return postService.getAvgRating(movieName);
    }

}
