package com.mypet.back_end.services;

import com.mypet.back_end.dto.AnimalDto;

import java.util.List;

public interface AnimalService {
    List<AnimalDto> findAllAnimals();
    List<AnimalDto> findAllAnimalsByPersonReference(String referencePerson);
    List<AnimalDto> findAllAnimalsByPersonReferenceNewPost(String referencePerson);
    AnimalDto addAnimal(AnimalDto animalDto);
    AnimalDto findAnimalByReferenceAnimal(String referenceAnimal);
    AnimalDto findAnimalById(Long id);
    AnimalDto updateAnimal(AnimalDto animalDto);
    boolean deleteAnimal(String referenceAnimal);


}
