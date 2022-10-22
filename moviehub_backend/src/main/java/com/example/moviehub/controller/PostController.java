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

    @PutMapping
    public Post updatePost(@RequestBody Post post){
        return postServiceImpl.updatePost(post);
    }

    @GetMapping
    public List<Post> getAllPost(){
        System.out.println("GET /post");
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());

        //get useremail from request token
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return postServiceImpl.getAllPost();
    }

    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable String id){
        postServiceImpl.deletePost(id);
        return id;
    }
    @GetMapping("/user={userId}")
    public ResponseEntity<String> getPostByUserId(@PathVariable String userId){
        List<Post> body = postServiceImpl.getPostByUserId(userId.replaceAll("\\{|\\}", ""));
        return ResponseEntity.ok().body(JsonUtil.toJsonString("Search Succeed", body));
    }

    @GetMapping("/movie={movieId}")
    public ResponseEntity<String> getPostByMovieId(@PathVariable String movieId){
//        String mid = movieId.replaceAll("\\{|\\}", "");
//        System.out.println(mid);
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
    public List<Document> getAvgRating(@RequestParam String movieName){
        return postServiceImpl.getAvgRating(movieName);
    }

}


