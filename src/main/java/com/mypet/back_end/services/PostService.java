package com.mypet.back_end.services;

import com.mypet.back_end.dto.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> getAllPosts();
    List<PostDto> findAllPostsByPersonReference(String referencePerson);
    PostDto createPost(PostDto postDto);
    PostDto updatePost(PostDto postDto);
    boolean deletePost(String referencePost);

}
