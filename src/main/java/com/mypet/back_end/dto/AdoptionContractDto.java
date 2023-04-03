package com.mypet.back_end.dto;

import com.mypet.back_end.entities.AnimalEntity;
import com.mypet.back_end.entities.PersonEntity;
import com.mypet.back_end.entities.PostEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdoptionContractDto {
    private Long id;
    private PersonEntity person;
    private String referencePerson;
    private PostEntity post;
    private String referencePost;
}
