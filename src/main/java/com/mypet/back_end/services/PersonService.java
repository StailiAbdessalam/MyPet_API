package com.mypet.back_end.services;

import com.mypet.back_end.dto.PersonDto;

import java.util.List;

public interface PersonService {
    PersonDto login(PersonDto personDto);
    List<PersonDto> getAllPersons();
    PersonDto addPerson(PersonDto personDto);
    PersonDto findPersonByEmail(String email);
    PersonDto findPersonByReferencePerson(String referencePerson);
    PersonDto findPersonById(Long id);
    PersonDto updatePerson(PersonDto personDto);
    void deletePerson(Long id);
    void deletePersonByReferencePerson(String referencePerson);
}