package com.mypet.back_end.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponse {
    private Long id;
    private String content;
    private PersonResponse person;
}
