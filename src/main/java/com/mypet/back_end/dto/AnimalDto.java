package com.mypet.back_end.dto;

import com.mypet.back_end.entities.PersonEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnimalDto {
    private Long id;
    private String referenceAnimal;
    private String name;
    private String type;
    private int age;
    private String picture;
    private PersonEntity person;
}
