package com.example.moviehub.controller;


import com.example.moviehub.collection.Post;
import com.example.moviehub.service.Impl.PostServiceImpl;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/post")
public class PostController {

    @Autowired
    private PostServiceImpl postServiceImpl;

    @PostMapping
    public Post save (@RequestBody Post post){
        return postServiceImpl.savePost(post);
    }

    @PutMapping
    public Post updatePost(@RequestBody Post post){
        return postServiceImpl.updatePost(post);
    }

    @GetMapping
    public List<Post> getAllPost(){
        return postServiceImpl.getAllPost();
    }

    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable String id){
        postServiceImpl.deletePost(id);
        return id;
    }

    @GetMapping("/{userid}")
    public List<Post> getPostByUserId(@PathVariable String userId){
        return postServiceImpl.getPostByUserId(userId);
    }

    @GetMapping("/getPostByName")
    public List<Post> getPostByName(@RequestParam String movieName){
        return postServiceImpl.getPostByName(movieName);
    }

    @GetMapping("/getAvgRatingByName")
    public List<Document> getAvgRating(@RequestParam String movieName){
        return postServiceImpl.getAvgRating(movieName);
    }

}
