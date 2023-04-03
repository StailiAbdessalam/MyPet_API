package com.mypet.back_end.servicesImp;

import com.mypet.back_end.dto.AdoptionContractDto;
import com.mypet.back_end.entities.AdoptionContractEntity;
import com.mypet.back_end.entities.PersonEntity;
import com.mypet.back_end.entities.PostEntity;
import com.mypet.back_end.repositories.AdoptionContractRepository;
import com.mypet.back_end.repositories.PersonRepository;
import com.mypet.back_end.repositories.PostRepository;
import com.mypet.back_end.services.AdoptionContractService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdoptionContractServiceImp implements AdoptionContractService {
    @Autowired
    AdoptionContractRepository adoptionContractRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    PostRepository postRepository;

    @Override
    public AdoptionContractDto createAdoptionContract(AdoptionContractDto adoptionContractDto) {
        PersonEntity personEntity = personRepository.findByReferencePerson(adoptionContractDto.getReferencePerson());
        PostEntity postEntity = postRepository.findByReferencePost(adoptionContractDto.getReferencePost());
        AdoptionContractEntity adoptionContractEntity = new AdoptionContractEntity();
        BeanUtils.copyProperties(adoptionContractDto, adoptionContractEntity);
        adoptionContractEntity.setPerson(personEntity);
        adoptionContractEntity.setPost(postEntity);
        AdoptionContractEntity storedAdoptionContract = adoptionContractRepository.save(adoptionContractEntity);
        AdoptionContractDto returnValue = new AdoptionContractDto();
        BeanUtils.copyProperties(storedAdoptionContract, returnValue);
        return returnValue;
    }

    @Override
    public AdoptionContractDto getAdoptionContractById(Long id) {
        AdoptionContractEntity adoptionContractEntity = adoptionContractRepository.findById(id).isPresent() ? adoptionContractRepository.findById(id).get() : null;
        if(adoptionContractEntity == null) return null;
        AdoptionContractDto returnValue = new AdoptionContractDto();
        BeanUtils.copyProperties(adoptionContractEntity, returnValue);
        return returnValue;
    }

    @Override
    public List<AdoptionContractDto> getAdoptionContractByReferencePerson(String referencePerson) {
        List<AdoptionContractEntity> adoptionContractEntityList = adoptionContractRepository.findByReferencePerson(referencePerson);
        List<AdoptionContractDto> returnValue = new ArrayList<>();
        for (AdoptionContractEntity adoptionContractEntity : adoptionContractEntityList) {
            AdoptionContractDto adoptionContractDto = new AdoptionContractDto();
            BeanUtils.copyProperties(adoptionContractEntity, adoptionContractDto);
            returnValue.add(adoptionContractDto);
        }
        return returnValue;
    }

    @Override
    public AdoptionContractDto getAdoptionContractByReferencePost(String referencePost) {
        AdoptionContractEntity adoptionContractEntity = adoptionContractRepository.findByReferencePost(referencePost);
        if(adoptionContractEntity == null) return null;
        System.out.println(adoptionContractEntity);
        AdoptionContractDto returnValue = new AdoptionContractDto();
        BeanUtils.copyProperties(adoptionContractEntity, returnValue);
        return returnValue;
    }
}
