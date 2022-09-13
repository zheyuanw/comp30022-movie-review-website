package com.example.moviehub.collection;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.List;

@Data
@Builder
@Document(collection = "user")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    @Id
    private String id;
    private String userName;
    private String password;
    private String email;
    private Integer age;
    private String photoId;

    public User(String id, String userName, String password, String email, Integer age, String photoId) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.age = age;
        this.photoId = photoId;
    }
}
