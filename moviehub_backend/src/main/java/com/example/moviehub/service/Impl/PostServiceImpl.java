package com.example.moviehub.service.Impl;

import com.example.moviehub.collection.Post;
import com.example.moviehub.collection.User;
import com.example.moviehub.collection.form.PostForm;
import com.example.moviehub.repository.PostRepository;
import com.example.moviehub.service.PostService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Post savePost(Post post) {

        return postRepository.save(post);
    }

    public Post publishPost(PostForm postForm, User user){
        return postRepository.save(
                new Post(user.getEmail(),
                        postForm.getMovieId(),
                        postForm.getReview(),
                        postForm.getRating()));
    }

    public Post updatePost(PostForm postForm, Post post) {
        post.setContent(postForm.getReview());
        post.setRating(postForm.getRating());
        return postRepository.save(post);
    }

    public List<Post> getAllPost() {

        return postRepository.findAll();
    }

    public List<Post> getPostByUserId(String userId) {

        return postRepository.findByUserId(userId);
    }

    public List<Document> getPostByMovieId(String movieId){
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.lookup(mongoTemplate.getCollectionName(User.class),
                        "userId", "email", "userinfo"),
                Aggregation.match(Criteria.where("movieId").is(movieId))
        );

        AggregationResults<Document> results = mongoTemplate.aggregate(agg, Post.class,Document.class);

        return  results.getMappedResults();
    }

    public List<Post> getPostByName(String movieName) {

        return postRepository.findByMovieName(movieName);
    }

    public Document getAvgRating(String movieId) {
        MatchOperation filterMovieName = Aggregation.match(Criteria.where("_id").is(movieId));

        GroupOperation groupByMovieNameAndSumRating = Aggregation.group("movieId","_id").sum("rating").as("totalRating");

        GroupOperation avgRating = Aggregation.group("_id.movieId").avg("totalRating").as("avgRating");

        Aggregation aggregation = Aggregation.newAggregation(groupByMovieNameAndSumRating,avgRating,filterMovieName);

        AggregationResults<Document> results = mongoTemplate.aggregate(aggregation, Post.class, Document.class);

        Document result = results.getUniqueMappedResult();
        return result;
    }

    public void deletePost(String id) {
        postRepository.deleteById(id);
    }

    public Optional<Post> getPostById(String id){
        return postRepository.findById(id);
    }
}
