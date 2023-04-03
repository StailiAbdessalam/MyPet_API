package com.mypet.back_end.dto;

import com.mypet.back_end.entities.PersonEntity;
import com.mypet.back_end.entities.PostEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
    private Long id;
    private String content;
    private String referencePost;
    private PostEntity post;
    private String referencePerson;
    private PersonEntity person;
}
