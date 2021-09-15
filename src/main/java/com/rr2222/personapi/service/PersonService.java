package com.rr2222.personapi.service;

import com.rr2222.personapi.dto.MessageResponseDTO;
import com.rr2222.personapi.dto.request.PersonDTO;
import com.rr2222.personapi.entity.Person;

import com.rr2222.personapi.exception.PersonNotFoundException;
import com.rr2222.personapi.repository.PersonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
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

    public List<PersonDTO> listAll() {
        List<Person> personList = personRepository.findAll();
        ModelMapper modelMapper = new ModelMapper();

        List<PersonDTO> dtos = personList.stream()
                .map(user -> modelMapper.map(user, PersonDTO.class))
                .collect(Collectors.toList());
        return dtos;
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Optional<Person> retorno = personRepository.findById(id);
        if (!retorno.isPresent()){
            throw new PersonNotFoundException(id);
        }
        ModelMapper modelMapper = new ModelMapper();
       return modelMapper.map(retorno.get(), PersonDTO.class);
    }

    private LocalDate convertStringToData(String data){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(data, formatter);
        return localDate;
    }
}
