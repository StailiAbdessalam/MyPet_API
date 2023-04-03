package com.mypet.back_end.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnimalRequest {
    private String referenceAnimal;
    private String name;
    private String type;
    private int age;
    private String picture;
    private String referencePerson;
}
