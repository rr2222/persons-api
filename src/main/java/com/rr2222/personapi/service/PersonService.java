package com.rr2222.personapi.service;

import com.rr2222.personapi.dto.MessageResponseDTO;
import com.rr2222.personapi.dto.request.PersonDTO;
import com.rr2222.personapi.entity.Person;

import com.rr2222.personapi.repository.PersonRepository;
import jdk.nashorn.internal.objects.NativeArray;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<PersonDTO> listAll() {
        List<Person> personList = personRepository.findAll();
        ModelMapper modelMapper = new ModelMapper();

        List<PersonDTO> dtos = personList.stream()
                .map(user -> modelMapper.map(user, PersonDTO.class))
                .collect(Collectors.toList());
        return dtos;
    }
}
