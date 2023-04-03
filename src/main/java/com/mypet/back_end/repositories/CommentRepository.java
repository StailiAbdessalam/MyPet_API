package com.mypet.back_end.repositories;

import com.mypet.back_end.entities.CommentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<CommentEntity, Long> {
    @Query("SELECT c FROM CommentEntity c WHERE c.post.referencePost = :referencePost")
    List<CommentEntity> findAllCommentsByPostReference(String referencePost);

}
