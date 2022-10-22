package com.example.moviehub.collection.form;

import org.springframework.web.multipart.MultipartFile;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class PhotoForm {

    private MultipartFile avatar;

    public PhotoForm(MultipartFile avatar) {
        this.avatar = avatar;
    }
}
