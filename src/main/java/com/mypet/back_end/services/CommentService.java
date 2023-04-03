package com.mypet.back_end.services;

import com.mypet.back_end.dto.CommentDto;

import java.util.List;

public interface CommentService {
    List<CommentDto> getAllComments();
    List<CommentDto> findAllCommentsByPostReference(String referencePost);
    CommentDto createComment(CommentDto commentDto);
    CommentDto updateComment(CommentDto commentDto);
    boolean deleteComment(Long id);

}

