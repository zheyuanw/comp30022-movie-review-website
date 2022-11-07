package com.example.moviehub.controller;


import com.example.moviehub.collection.Like;
import com.example.moviehub.collection.Post;
import com.example.moviehub.collection.User;
import com.example.moviehub.collection.form.PostForm;
import com.example.moviehub.service.Impl.LikeServiceImpl;
import com.example.moviehub.service.Impl.PostServiceImpl;
import com.example.moviehub.service.Impl.UserServiceImpl;
import com.example.moviehub.util.JsonUtil;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/post")
public class PostController {

    @Autowired
    private PostServiceImpl postServiceImpl;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private LikeServiceImpl likeServiceImpl;


    @PostMapping
    public Post save (@RequestBody PostForm postForm){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return postServiceImpl.publishPost(postForm, userServiceImpl.getUserByEmail(email));
    }

    @PutMapping("/{id}")
    public ResponseEntity updatePost(@RequestBody PostForm postForm, @PathVariable String id){

        //remove { and } which will be parsed by @PathVariable
        Optional<Post> optionalPost = postServiceImpl.getPostById(id.replaceAll("\\{|\\}", ""));

        if (optionalPost.isPresent()){
            Post post = optionalPost.get();
            postServiceImpl.updatePost(postForm, post);
            return ResponseEntity.ok().body(JsonUtil.toJsonString("Edit Success"));
        }else {
            return ResponseEntity.badRequest().body(JsonUtil.toJsonString("Post Not Found"));
        }
    }

    @GetMapping
    public List<Post> getAllPost(){
        return postServiceImpl.getAllPost();
    }

    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable String id){
        //remove { and } which will be parsed by @PathVariable
        postServiceImpl.deletePost(id.replaceAll("\\{|\\}", ""));
        return id;
    }
    @GetMapping("/user={userId}")
    public ResponseEntity<String> getPostByUserId(@PathVariable String userId){

        //remove { and } which will be parsed by @PathVariable
        List<Post> body = postServiceImpl.getPostByUserId(userId.replaceAll("\\{|\\}", ""));
        return ResponseEntity.ok().body(JsonUtil.toJsonString("Search Succeed", body));
    }

    @GetMapping("/movie={movieId}")
    public ResponseEntity<String> getPostByMovieId(@PathVariable String movieId){

        //remove { and } which will be parsed by @PathVariable
        List<Post> body = postServiceImpl.getPostByMovieId(movieId.replaceAll("\\{|\\}", ""));
        return ResponseEntity.ok().body(JsonUtil.toJsonString("Search Succeed", body));
    }


    @PostMapping("/post={postId}/like")
    public ResponseEntity<String> likePost(@PathVariable String postId){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        if (likeServiceImpl.like(postId, email)){
            return ResponseEntity.ok().body(JsonUtil.toJsonString("Like Operation Succeed"));
        }else{
            return ResponseEntity.badRequest().body(JsonUtil.toJsonString("Like Operation Failed"));
        }
    }

    @PostMapping("/post={postId}/dislike")
    public ResponseEntity<String> dislikePost(@PathVariable String postId){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        if (likeServiceImpl.dislike(postId, email)){
            return ResponseEntity.ok().body(JsonUtil.toJsonString("Dislike Operation Succeed"));
        }else{
            return ResponseEntity.badRequest().body(JsonUtil.toJsonString("Dislike Operation Failed"));
        }
    }

    @GetMapping("/post={postId}/like")
    public ResponseEntity<String> getPostLike(@PathVariable String postId){
        return ResponseEntity.ok().body(JsonUtil.toJsonString("Operation Succeed",
                likeServiceImpl.countLikeDislike(postId, Like.Status.LIKE)));
    }

    @GetMapping("/post={postId}/dislike")
    public ResponseEntity<String> getPostDislike(@PathVariable String postId){
        return ResponseEntity.ok().body(JsonUtil.toJsonString("Operation Succeed",
                likeServiceImpl.countLikeDislike(postId, Like.Status.DISLIKE)));
    }

    @GetMapping("/getPostByName")
    public List<Post> getPostByName(@RequestParam String movieName){
        return postServiceImpl.getPostByName(movieName);
    }

    @GetMapping("/getAvgRatingByName")
    public Document getAvgRating(@RequestParam String movieId){
        return postServiceImpl.getAvgRating(movieId);
    }

}


