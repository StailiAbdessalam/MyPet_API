package com.mypet.back_end.repositories;

import com.mypet.back_end.entities.AdoptionContractEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdoptionContractRepository extends CrudRepository<AdoptionContractEntity, Long> {
    @Query("SELECT a FROM AdoptionContractEntity a WHERE a.person.referencePerson = :referencePerson")
    List<AdoptionContractEntity> findByReferencePerson(String referencePerson);

    @Query("SELECT a FROM AdoptionContractEntity a WHERE a.post.referencePost = :referencePost")
    AdoptionContractEntity findByReferencePost(String referencePost);
}
