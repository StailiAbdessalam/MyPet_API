package com.mypet.back_end.controllers;

import com.mypet.back_end.dto.AdoptionContractDto;
import com.mypet.back_end.requests.AdoptionContractRequest;
import com.mypet.back_end.responses.AdoptionContractResponse;
import com.mypet.back_end.responses.AnimalResponse;
import com.mypet.back_end.responses.PersonResponse;
import com.mypet.back_end.responses.PostResponse;
import com.mypet.back_end.services.AdoptionContractService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/adopt")
public class AdoptionContractController {
    @Autowired
    AdoptionContractService adoptionContractService;

    @PostMapping("/add")
    public ResponseEntity<AdoptionContractResponse> createAdoptionContract(@RequestBody AdoptionContractRequest adoptionContractRequest) {
        AdoptionContractDto adoptionContractDto = new AdoptionContractDto();
        BeanUtils.copyProperties(adoptionContractRequest, adoptionContractDto);
        AdoptionContractDto createdAdoptionContract = adoptionContractService.createAdoptionContract(adoptionContractDto);
        AdoptionContractResponse adoptionContractResponse = new AdoptionContractResponse();
        BeanUtils.copyProperties(createdAdoptionContract, adoptionContractResponse);
        PostResponse postResponse = new PostResponse();
        BeanUtils.copyProperties(createdAdoptionContract.getPost(), postResponse);
        adoptionContractResponse.setPost(postResponse);
        PersonResponse personResponse = new PersonResponse();
        BeanUtils.copyProperties(createdAdoptionContract.getPerson(), personResponse);
        adoptionContractResponse.setPerson(personResponse);
        return ResponseEntity.ok(adoptionContractResponse);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<AdoptionContractResponse> getAdoptionContractById(@PathVariable Long id) {
        AdoptionContractDto adoptionContractDto = adoptionContractService.getAdoptionContractById(id);
        AdoptionContractResponse adoptionContractResponse = new AdoptionContractResponse();
        BeanUtils.copyProperties(adoptionContractDto, adoptionContractResponse);
        PostResponse postResponse = new PostResponse();
        BeanUtils.copyProperties(adoptionContractDto.getPost(), postResponse);
        adoptionContractResponse.setPost(postResponse);
        PersonResponse personResponse = new PersonResponse();
        BeanUtils.copyProperties(adoptionContractDto.getPerson(), personResponse);
        adoptionContractResponse.setPerson(personResponse);
        return ResponseEntity.ok(adoptionContractResponse);
    }

    @GetMapping("/by-person/{referencePerson}")
    public ResponseEntity<List<AdoptionContractResponse>> getAdoptionContractByReferencePerson(@PathVariable String referencePerson) {
        List<AdoptionContractDto> adoptionContractDtoList = adoptionContractService.getAdoptionContractByReferencePerson(referencePerson);
        List<AdoptionContractResponse> adoptionContractResponseList = new ArrayList<>();
        for (AdoptionContractDto adoptionContractDto : adoptionContractDtoList) {
            AdoptionContractResponse adoptionContractResponse = new AdoptionContractResponse();
            BeanUtils.copyProperties(adoptionContractDto, adoptionContractResponse);
            PostResponse postResponse = new PostResponse();
            BeanUtils.copyProperties(adoptionContractDto.getPost(), postResponse);
            adoptionContractResponse.setPost(postResponse);
            PersonResponse personResponse = new PersonResponse();
            BeanUtils.copyProperties(adoptionContractDto.getPerson(), personResponse);
            adoptionContractResponse.setPerson(personResponse);
            adoptionContractResponseList.add(adoptionContractResponse);
            AnimalResponse animalResponse = new AnimalResponse();
            BeanUtils.copyProperties(adoptionContractDto.getPost().getAnimal(), animalResponse);
            postResponse.setAnimal(animalResponse);
        }
        return ResponseEntity.ok(adoptionContractResponseList);
    }

}
