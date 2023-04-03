package com.mypet.back_end.servicesImp;

import com.mypet.back_end.dto.PostDto;
import com.mypet.back_end.entities.PostEntity;
import com.mypet.back_end.repositories.AnimalRepository;
import com.mypet.back_end.repositories.PersonRepository;
import com.mypet.back_end.repositories.PostRepository;
import com.mypet.back_end.services.PostService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImp implements PostService {
    @Autowired
    PostRepository postRepository;
    @Autowired
    AnimalRepository animalRepository;
    @Autowired
    PersonRepository personRepository;

    @Override
    public List<PostDto> getAllPosts() {
        List<PostDto> postDtoList = new ArrayList<>();
        List<PostEntity> postEntityList = postRepository.getAllPosts();
        for (PostEntity postEntity : postEntityList) {
            PostDto postDto = new PostDto();
            BeanUtils.copyProperties(postEntity, postDto);
            postDtoList.add(postDto);
        }
        return postDtoList;
    }

    @Override
    public List<PostDto> findAllPostsByPersonReference(String referencePerson) {
        List<PostEntity> postEntities = postRepository.findAllPostsByPersonReference(referencePerson);
        List<PostDto> postDtos = new ArrayList<>();
        for (PostEntity postEntity : postEntities) {
            PostDto postDto1 = new PostDto();
            BeanUtils.copyProperties(postEntity, postDto1);
            postDtos.add(postDto1);
        }
        return postDtos;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        postDto.setPerson(personRepository.findByReferencePerson(postDto.getReferencePerson()));
        postDto.setAnimal(animalRepository.findByReferenceAnimal(postDto.getReferenceAnimal()));
        postDto.setReferencePost(postDto.getDays() + "-" + postDto.getReferenceAnimal());
        PostEntity postEntity = new PostEntity();
        BeanUtils.copyProperties(postDto, postEntity);
        PostEntity createdPost = postRepository.save(postEntity);
        PostDto postDto1 = new PostDto();
        BeanUtils.copyProperties(createdPost, postDto1);
        return postDto1;
    }

    @Override
    public PostDto updatePost(PostDto postDto) {
        PostEntity postEntity = postRepository.findByReferencePost(postDto.getReferencePost());
        if (postEntity == null) return null;
        postEntity.setTitle(postDto.getTitle());
        postEntity.setDescription(postDto.getDescription());
        postEntity.setDays(postDto.getDays());
        PostEntity updatedPost = postRepository.save(postEntity);
        PostDto postDto1 = new PostDto();
        BeanUtils.copyProperties(updatedPost, postDto1);
        return postDto1;
    }

    @Override
    public boolean deletePost(String referencePost) {
        PostEntity postEntity = postRepository.findByReferencePost(referencePost);
        if (postEntity == null) return false;
        postRepository.delete(postEntity);
        return true;
    }
}
