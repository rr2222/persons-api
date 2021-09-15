package com.rr2222.personapi.service;

import com.rr2222.personapi.dto.MessageResponseDTO;
import com.rr2222.personapi.dto.request.PersonDTO;
import com.rr2222.personapi.entity.Person;

import com.rr2222.personapi.repository.PersonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;


    public MessageResponseDTO createPerson(PersonDTO personDTO){
        ModelMapper modelMapper = new ModelMapper();
        Person personToSave = modelMapper.map(personDTO, Person.class);
        personToSave.setBirthDate(convertStringToData(personDTO.getBirthDate()));
        Person savedPerson = personRepository.save(personToSave);
        return MessageResponseDTO.builder()
                .message("Created person with id" + savedPerson.getId())
                .build();
    }

    private LocalDate convertStringToData(String data){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(data, formatter);
        return localDate;
    }
}
