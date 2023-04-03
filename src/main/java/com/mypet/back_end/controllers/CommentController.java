package com.mypet.back_end.controllers;

import com.mypet.back_end.dto.CommentDto;
import com.mypet.back_end.requests.CommentRequest;
import com.mypet.back_end.responses.CommentResponse;
import com.mypet.back_end.responses.PersonResponse;
import com.mypet.back_end.responses.PostResponse;
import com.mypet.back_end.services.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/all")
    public ResponseEntity<List<CommentResponse>> getAllComments() {
        List<CommentDto> commentDtos = commentService.getAllComments();
        List<CommentResponse> commentResponses = new ArrayList<>();
        for (CommentDto commentDto : commentDtos) {
            CommentResponse commentResponse = new CommentResponse();
            BeanUtils.copyProperties(commentDto, commentResponse);
            commentResponses.add(commentResponse);
        }
        return ResponseEntity.ok(commentResponses);
    }

    @GetMapping("/by-post/{referencePost}")
    public ResponseEntity<List<CommentResponse>> getAllCommentsByPostReference(@PathVariable String referencePost) {
        List<CommentDto> commentDtos = commentService.findAllCommentsByPostReference(referencePost);
        List<CommentResponse> commentResponses = new ArrayList<>();
        for (CommentDto commentDto : commentDtos) {
            PersonResponse personResponse = new PersonResponse();
            BeanUtils.copyProperties(commentDto.getPerson(), personResponse);
            CommentResponse commentResponse = new CommentResponse();
            BeanUtils.copyProperties(commentDto, commentResponse);
            commentResponse.setPerson(personResponse);
            commentResponses.add(commentResponse);
        }
        return ResponseEntity.ok(commentResponses);
    }

    @PostMapping("/add")
    public ResponseEntity<CommentResponse> createComment(@RequestBody CommentRequest commentRequest) {
        CommentDto commentDto = new CommentDto();
        BeanUtils.copyProperties(commentRequest, commentDto);
        CommentDto createdComment = commentService.createComment(commentDto);
        CommentResponse commentResponse = new CommentResponse();
        BeanUtils.copyProperties(createdComment, commentResponse);
        return ResponseEntity.ok(commentResponse);
    }

    @PostMapping("/update")
    public ResponseEntity<CommentResponse> updateComment(CommentDto commentDto) {
        CommentDto updatedComment = commentService.updateComment(commentDto);
        CommentResponse commentResponse = new CommentResponse();
        BeanUtils.copyProperties(updatedComment, commentResponse);
        return ResponseEntity.ok(commentResponse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteComment(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.deleteComment(id));
    }

}
