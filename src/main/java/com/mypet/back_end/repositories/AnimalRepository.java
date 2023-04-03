package com.mypet.back_end.repositories;

import com.mypet.back_end.entities.AnimalEntity;
import com.mypet.back_end.entities.PersonEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends CrudRepository<AnimalEntity, Long> {
    @Query("SELECT a FROM AnimalEntity a")
    List<AnimalEntity> getAllAnimals();

    @Query("SELECT a FROM AnimalEntity a where a.person.referencePerson = :referencePerson")
    List<AnimalEntity> findByPersonReference(String referencePerson);

    // get animals that are not in the post table
    @Query("SELECT a FROM AnimalEntity a where a.person.referencePerson = :referencePerson and a.referenceAnimal not in (select p.animal.referenceAnimal from PostEntity p where p.person.referencePerson = :referencePerson)")
    List<AnimalEntity> findByPersonReferenceNewPost(String referencePerson);

    AnimalEntity findByReferenceAnimal(String referenceAnimal);


}
