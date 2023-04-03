package com.mypet.back_end.services;

import com.mypet.back_end.dto.AdoptionContractDto;

import java.util.List;

public interface AdoptionContractService {
    AdoptionContractDto createAdoptionContract(AdoptionContractDto adoptionContractDto);
    AdoptionContractDto getAdoptionContractById(Long id);
    List<AdoptionContractDto> getAdoptionContractByReferencePerson(String referencePerson);
    AdoptionContractDto getAdoptionContractByReferencePost(String referencePost);

}
