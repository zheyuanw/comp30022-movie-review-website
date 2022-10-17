package com.example.moviehub.collection.form;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostForm {

    private String movieId;
    private String review;
    private Double rate;

    public PostForm(String movieId, String review, Double rate) {
        this.movieId = movieId;
        this.review = review;
        this.rate = rate;
    }
}
