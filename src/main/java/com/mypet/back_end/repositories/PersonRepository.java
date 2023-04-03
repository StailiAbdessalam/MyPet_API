package com.mypet.back_end.repositories;

import com.mypet.back_end.entities.PersonEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<PersonEntity, Long> {
    @Query("SELECT p FROM PersonEntity p")
    List<PersonEntity> getAllPersons();
    PersonEntity findByEmail(String email);
    PersonEntity findByReferencePerson(String referencePerson);
    void deleteByReferencePerson(String referencePerson);
}
