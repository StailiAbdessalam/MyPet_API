package com.mypet.back_end.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequest {
    private Long id;
    private String content;
    private String referencePerson;
    private String referencePost;
}
