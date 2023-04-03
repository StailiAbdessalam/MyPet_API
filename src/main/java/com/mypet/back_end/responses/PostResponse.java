package com.mypet.back_end.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostResponse {
    private String referencePost;
    private String title;
    private String description;
    private int days;
    private PersonResponse person;
    private AnimalResponse animal;
    private int commentNumber = 0;
    private boolean adopted = false;
}
