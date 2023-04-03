package com.mypet.back_end.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnimalResponse {
    private String referenceAnimal;
    private String name;
    private String type;
    private int age;
    private String picture;
}
