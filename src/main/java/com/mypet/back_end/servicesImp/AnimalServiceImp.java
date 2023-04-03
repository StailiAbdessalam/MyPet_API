package com.mypet.back_end.servicesImp;

import com.mypet.back_end.dto.AnimalDto;
import com.mypet.back_end.entities.AnimalEntity;
import com.mypet.back_end.repositories.AnimalRepository;
import com.mypet.back_end.services.AnimalService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnimalServiceImp implements AnimalService {
    @Autowired
    AnimalRepository animalRepository;

    @Override
    public List<AnimalDto> findAllAnimals() {
        List<AnimalEntity> animalEntities = animalRepository.getAllAnimals();
        List<AnimalDto> animalDtos = new ArrayList<>();
        for (AnimalEntity animalEntity : animalEntities) {
            AnimalDto animalDto = new AnimalDto();
            BeanUtils.copyProperties(animalEntity, animalDto);
            animalDtos.add(animalDto);
        }
        return animalDtos;
    }

    @Override
    public List<AnimalDto> findAllAnimalsByPersonReference(String referencePerson) {
        List<AnimalEntity> animalEntities = animalRepository.findByPersonReference(referencePerson);
        List<AnimalDto> animalDtos = new ArrayList<>();
        for (AnimalEntity animalEntity : animalEntities) {
            AnimalDto animalDto = new AnimalDto();
            BeanUtils.copyProperties(animalEntity, animalDto);
            animalDtos.add(animalDto);
        }
        return animalDtos;
    }

    @Override
    public List<AnimalDto> findAllAnimalsByPersonReferenceNewPost(String referencePerson) {
        List<AnimalEntity> animalEntities = animalRepository.findByPersonReferenceNewPost(referencePerson);
        List<AnimalDto> animalDtos = new ArrayList<>();
        for (AnimalEntity animalEntity : animalEntities) {
            AnimalDto animalDto = new AnimalDto();
            BeanUtils.copyProperties(animalEntity, animalDto);
            animalDtos.add(animalDto);
        }
        return animalDtos;
    }

    @Override
    public AnimalDto addAnimal(AnimalDto animalDto) {
        animalDto.setReferenceAnimal(animalDto.getType() + "-" + animalDto.getName() + "-" + animalDto.getAge());
        AnimalEntity animalEntity = new AnimalEntity();
        BeanUtils.copyProperties(animalDto, animalEntity);
        AnimalEntity createdAnimal = animalRepository.save(animalEntity);
        AnimalDto animalDtoResponse = new AnimalDto();
        BeanUtils.copyProperties(createdAnimal, animalDtoResponse);
        return animalDtoResponse;
    }

    @Override
    public AnimalDto findAnimalByReferenceAnimal(String referenceAnimal) {
        AnimalEntity animalEntity = animalRepository.findByReferenceAnimal(referenceAnimal);
        AnimalDto animalDto = new AnimalDto();
        BeanUtils.copyProperties(animalEntity, animalDto);
        return animalDto;
    }

    @Override
    public AnimalDto findAnimalById(Long id) {
        return null;
    }

    @Override
    public AnimalDto updateAnimal(AnimalDto animalDto) {
        return null;
    }

    @Override
    public boolean deleteAnimal(String referenceAnimal) {
        AnimalEntity animalEntity = animalRepository.findByReferenceAnimal(referenceAnimal);
        if (animalEntity == null) throw new RuntimeException("Animal not found");
        animalRepository.delete(animalEntity);
        return true;
    }
}
