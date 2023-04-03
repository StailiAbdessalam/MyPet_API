package com.mypet.back_end.dto;

import com.mypet.back_end.entities.AnimalEntity;
import com.mypet.back_end.entities.PersonEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto {
    private Long id;
    private String referencePost;
    private String title;
    private String description;
    private int days;
    private String referencePerson;
    private PersonEntity person;
    private String referenceAnimal;
    private AnimalEntity animal;
}
