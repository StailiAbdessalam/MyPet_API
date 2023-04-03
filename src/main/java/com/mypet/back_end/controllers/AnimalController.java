package com.mypet.back_end.controllers;

import com.mypet.back_end.dto.AnimalDto;
import com.mypet.back_end.dto.PersonDto;
import com.mypet.back_end.entities.PersonEntity;
import com.mypet.back_end.requests.AnimalRequest;
import com.mypet.back_end.responses.AnimalResponse;
import com.mypet.back_end.services.AnimalService;
import com.mypet.back_end.services.PersonService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/animal")
public class AnimalController {

    @Autowired
    AnimalService animalService;
    @Autowired
    PersonService personService;

    @PostMapping("/add")
    public ResponseEntity<AnimalResponse> addAnimal(@RequestBody AnimalRequest animalRequest) {
        PersonDto personDto = personService.findPersonByReferencePerson(animalRequest.getReferencePerson());
        PersonEntity personEntity = new PersonEntity();
        BeanUtils.copyProperties(personDto, personEntity);
        AnimalDto animalDto = new AnimalDto();
        BeanUtils.copyProperties(animalRequest, animalDto);
        animalDto.setPerson(personEntity);
        AnimalDto createdAnimal = animalService.addAnimal(animalDto);
        AnimalResponse animalResponse = new AnimalResponse();
        BeanUtils.copyProperties(createdAnimal, animalResponse);
        return ResponseEntity.ok(animalResponse);
    }

    @GetMapping("/by-person/{referencePerson}")
    public ResponseEntity<List<AnimalResponse>> findAllAnimalsByPersonReference(@PathVariable String referencePerson) {
        List<AnimalDto> animalDtos = animalService.findAllAnimalsByPersonReference(referencePerson);
        List<AnimalResponse> animalResponses = new ArrayList<>();
        for (AnimalDto animalDto : animalDtos) {
            AnimalResponse animalResponse = new AnimalResponse();
            BeanUtils.copyProperties(animalDto, animalResponse);
            animalResponses.add(animalResponse);
        }
        return ResponseEntity.ok(animalResponses);
    }

    @GetMapping("/by-person-new-post/{referencePerson}")
    public ResponseEntity<List<AnimalResponse>> findAllAnimalsByPersonReferenceForNewPost(@PathVariable String referencePerson) {
        List<AnimalDto> animalDtos = animalService.findAllAnimalsByPersonReferenceNewPost(referencePerson);
        List<AnimalResponse> animalResponses = new ArrayList<>();
        for (AnimalDto animalDto : animalDtos) {
            AnimalResponse animalResponse = new AnimalResponse();
            BeanUtils.copyProperties(animalDto, animalResponse);
            animalResponses.add(animalResponse);
        }
        return ResponseEntity.ok(animalResponses);
    }

    @DeleteMapping("/delete/{referenceAnimal}")
    public ResponseEntity<Boolean> deleteAnimal(@PathVariable String referenceAnimal) {

        return ResponseEntity.ok(animalService.deleteAnimal(referenceAnimal));
    }


    @GetMapping("/find/{referenceAnimal}")
    public ResponseEntity<AnimalResponse> findAnimalByReferenceAnimal(@PathVariable String referenceAnimal) {
        AnimalDto animalDto = animalService.findAnimalByReferenceAnimal(referenceAnimal);
        AnimalResponse animalResponse = new AnimalResponse();
        BeanUtils.copyProperties(animalDto, animalResponse);
        return ResponseEntity.ok(animalResponse);
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<AnimalDto>> findAllAnimals() {
        return ResponseEntity.ok(animalService.findAllAnimals());
    }

    @PutMapping("/update")
    public ResponseEntity<AnimalResponse> updateAnimal(@RequestBody AnimalRequest animalRequest) {
        AnimalDto animalDto = new AnimalDto();
        BeanUtils.copyProperties(animalRequest, animalDto);
        AnimalDto updatedAnimal = animalService.updateAnimal(animalDto);
        AnimalResponse animalResponse = new AnimalResponse();
        BeanUtils.copyProperties(updatedAnimal, animalResponse);
        return ResponseEntity.ok(animalResponse);
    }
}
