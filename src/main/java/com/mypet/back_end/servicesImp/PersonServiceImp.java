package com.mypet.back_end.servicesImp;

import com.mypet.back_end.dto.PersonDto;
import com.mypet.back_end.entities.PersonEntity;
import com.mypet.back_end.repositories.PersonRepository;
import com.mypet.back_end.services.PersonService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImp implements PersonService {
    @Autowired
    PersonRepository personRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public PersonDto login(PersonDto personDto) {
        PersonEntity personEntity = personRepository.findByEmail(personDto.getEmail());
        if (personEntity != null) {
            if (passwordEncoder.matches(personDto.getPassword(), personEntity.getPassword())) {
                PersonDto loggedInPersonDto = new PersonDto();
                BeanUtils.copyProperties(personEntity, loggedInPersonDto);
                return loggedInPersonDto;
            }
        }
        return null;
    }

    @Override
    public List<PersonDto> getAllPersons() {
        List<PersonEntity> personEntities = personRepository.getAllPersons();
        List<PersonDto> personDtos = new ArrayList<PersonDto>();
        for (PersonEntity personEntity : personEntities) {
            PersonDto personDto = new PersonDto();
            BeanUtils.copyProperties(personEntity, personDto);
            personDtos.add(personDto);
        }
        return personDtos;
    }

    @Override
    public PersonDto addPerson(PersonDto personDto) {
        personDto.setReferencePerson(personDto.getFirstName().toLowerCase()+"_"+personDto.getLastName().toLowerCase());
        personDto.setPassword(passwordEncoder.encode(personDto.getPassword()));
        PersonEntity personEntity = new PersonEntity();
        BeanUtils.copyProperties(personDto, personEntity);
        PersonEntity createdPerson = personRepository.save(personEntity);
        PersonDto createdPersonDto = new PersonDto();
        BeanUtils.copyProperties(createdPerson, createdPersonDto);
        return createdPersonDto;
    }

    @Override
    public PersonDto findPersonByEmail(String email) {
        PersonEntity personEntity = personRepository.findByEmail(email);
        PersonDto personDto = new PersonDto();
        BeanUtils.copyProperties(personEntity, personDto);
        return personDto;
    }

    @Override
    public PersonDto findPersonByReferencePerson(String referencePerson) {
        PersonEntity personEntity = personRepository.findByReferencePerson(referencePerson);
        PersonDto personDto = new PersonDto();
        BeanUtils.copyProperties(personEntity, personDto);
        return personDto;
    }

    @Override
    public PersonDto findPersonById(Long id) {
        PersonEntity personEntity = personRepository.findById(id).get();
        PersonDto personDto = new PersonDto();
        BeanUtils.copyProperties(personEntity, personDto);
        return personDto;
    }

    @Override
    public PersonDto updatePerson(PersonDto personDto) {
        PersonEntity personEntity = personRepository.findByReferencePerson(personDto.getReferencePerson());
        personEntity.setFirstName(personDto.getFirstName());
        personEntity.setLastName(personDto.getLastName());
        personEntity.setAddress(personDto.getAddress());
        personEntity.setCity(personDto.getCity());
        personEntity.setPhone(personDto.getPhone());
        personEntity.setPassword(passwordEncoder.encode(personDto.getPassword()));
        PersonEntity updatedPerson = personRepository.save(personEntity);
        PersonDto updatedPersonDto = new PersonDto();
        BeanUtils.copyProperties(updatedPerson, updatedPersonDto);
        return updatedPersonDto;
    }

    @Override
    public void deletePerson(Long id) {
        PersonEntity personEntity = personRepository.findById(id).get();
        personRepository.delete(personEntity);
    }

    @Override
    public void deletePersonByReferencePerson(String referencePerson) {
        PersonEntity personEntity = personRepository.findByReferencePerson(referencePerson);
        personRepository.delete(personEntity);
    }
}
