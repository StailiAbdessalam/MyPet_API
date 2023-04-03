package com.mypet.back_end.repositories;

import com.mypet.back_end.entities.PostEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<PostEntity, Long> {

    @Query("SELECT p FROM PostEntity p ORDER BY p.id DESC")
    List<PostEntity> getAllPosts();

    @Query("SELECT p FROM PostEntity p WHERE p.person.referencePerson = :referencePerson ORDER BY p.id DESC")
    List<PostEntity> findAllPostsByPersonReference(String referencePerson);

    PostEntity findByReferencePost(String referencePost);


}
