package com.mypet.back_end.controllers;

import com.mypet.back_end.dto.CommentDto;
import com.mypet.back_end.dto.PostDto;
import com.mypet.back_end.requests.PostRequest;
import com.mypet.back_end.responses.AnimalResponse;
import com.mypet.back_end.responses.PersonResponse;
import com.mypet.back_end.responses.PostResponse;
import com.mypet.back_end.services.AdoptionContractService;
import com.mypet.back_end.services.CommentService;
import com.mypet.back_end.services.PostService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/post")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private AdoptionContractService adoptionContractService;


    @GetMapping("/all")
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        List<PostDto> postDtos = postService.getAllPosts();
        List<PostResponse> postResponses = new ArrayList<>();
        for (PostDto postDto : postDtos) {
            PostResponse postResponse = new PostResponse();
            BeanUtils.copyProperties(postDto, postResponse);
            PersonResponse personResponse = new PersonResponse();
            BeanUtils.copyProperties(postDto.getPerson(), personResponse);
            postResponse.setPerson(personResponse);
            AnimalResponse animalResponse = new AnimalResponse();
            BeanUtils.copyProperties(postDto.getAnimal(), animalResponse);
            postResponse.setAnimal(animalResponse);
            List<CommentDto> commentDtos = commentService.findAllCommentsByPostReference(postDto.getReferencePost());
            postResponse.setCommentNumber(commentDtos.size());
            postResponses.add(postResponse);
            if(adoptionContractService.getAdoptionContractByReferencePost(postResponse.getReferencePost()) != null){
                postResponse.setAdopted(true);
            }
        }
        return ResponseEntity.ok(postResponses);
    }

    @GetMapping("/by-person/{referencePerson}")
    public ResponseEntity<List<PostResponse>> getAllPostsByPersonReference(@PathVariable String referencePerson) {
        List<PostDto> postDtos = postService.findAllPostsByPersonReference(referencePerson);
        List<PostResponse> postResponses = new ArrayList<>();
        for (PostDto postDto : postDtos) {
            PostResponse postResponse = new PostResponse();
            BeanUtils.copyProperties(postDto, postResponse);
            PersonResponse personResponse = new PersonResponse();
            BeanUtils.copyProperties(postDto.getPerson(), personResponse);
            postResponse.setPerson(personResponse);
            AnimalResponse animalResponse = new AnimalResponse();
            BeanUtils.copyProperties(postDto.getAnimal(), animalResponse);
            postResponse.setAnimal(animalResponse);
            List<CommentDto> commentDtos = commentService.findAllCommentsByPostReference(postDto.getReferencePost());
            postResponse.setCommentNumber(commentDtos.size());
            postResponses.add(postResponse);
        }
        return ResponseEntity.ok(postResponses);
    }

    @PostMapping("/add")
    public ResponseEntity<PostResponse> createPost(@RequestBody PostRequest postRequest) {
        PostDto postDto = new PostDto();
        BeanUtils.copyProperties(postRequest, postDto);
        PostDto createdPost = postService.createPost(postDto);
        PostResponse postResponse = new PostResponse();
        BeanUtils.copyProperties(createdPost, postResponse);
        return ResponseEntity.ok(postResponse);
    }

    @PostMapping("/update")
    public ResponseEntity<PostResponse> updatePost(@RequestBody PostDto postDto) {
        PostDto updatedPost = postService.updatePost(postDto);
        PostResponse postResponse = new PostResponse();
        BeanUtils.copyProperties(updatedPost, postResponse);
        return ResponseEntity.ok(postResponse);
    }

    @PostMapping("/delete/{referencePost}")
    public ResponseEntity<Boolean> deletePost(@PathVariable String referencePost) {
        return ResponseEntity.ok(postService.deletePost(referencePost));
    }




}
