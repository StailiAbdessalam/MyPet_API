package com.mypet.back_end.servicesImp;

import com.mypet.back_end.dto.CommentDto;
import com.mypet.back_end.entities.CommentEntity;
import com.mypet.back_end.entities.PersonEntity;
import com.mypet.back_end.entities.PostEntity;
import com.mypet.back_end.repositories.CommentRepository;
import com.mypet.back_end.repositories.PersonRepository;
import com.mypet.back_end.repositories.PostRepository;
import com.mypet.back_end.services.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImp implements CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    PostRepository postRepository;

    @Override
    public List<CommentDto> getAllComments() {
        return null;
    }

    @Override
    public List<CommentDto> findAllCommentsByPostReference(String referencePost) {
        List<CommentEntity> commentEntities = commentRepository.findAllCommentsByPostReference(referencePost);
        List<CommentDto> commentDtos = new ArrayList<>();
        for (CommentEntity commentEntity : commentEntities) {
            CommentDto commentDto = new CommentDto();
            BeanUtils.copyProperties(commentEntity, commentDto);
            commentDtos.add(commentDto);
        }
        return commentDtos;
    }

    @Override
    public CommentDto createComment(CommentDto commentDto) {
        PersonEntity personEntity = personRepository.findByReferencePerson(commentDto.getReferencePerson());
        PostEntity postEntity = postRepository.findByReferencePost(commentDto.getReferencePost());
        CommentEntity commentEntity = new CommentEntity();
        BeanUtils.copyProperties(commentDto, commentEntity);
        commentEntity.setPerson(personEntity);
        commentEntity.setPost(postEntity);
        CommentEntity storedComment = commentRepository.save(commentEntity);
        CommentDto returnValue = new CommentDto();
        BeanUtils.copyProperties(storedComment, returnValue);
        return returnValue;
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto) {
        CommentEntity commentEntity = commentRepository.findById(commentDto.getId()).isPresent() ? commentRepository.findById(commentDto.getId()).get() : null;
        if (commentEntity != null) {
            BeanUtils.copyProperties(commentDto, commentEntity);
            CommentEntity storedComment = commentRepository.save(commentEntity);
            CommentDto returnValue = new CommentDto();
            BeanUtils.copyProperties(storedComment, returnValue);
            return returnValue;
        }
        return null;
    }

    @Override
    public boolean deleteComment(Long id) {
        CommentEntity commentEntity = commentRepository.findById(id).isPresent() ? commentRepository.findById(id).get() : null;
        if (commentEntity != null) {
            commentRepository.delete(commentEntity);
            return true;
        }
        return false;
    }
}
