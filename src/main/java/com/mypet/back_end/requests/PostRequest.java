package com.mypet.back_end.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequest {
    private String title;
    private String description;
    private int days;
    private String referencePerson;
    private String referenceAnimal;
}
