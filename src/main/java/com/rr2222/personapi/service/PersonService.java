package com.rr2222.personapi.service;

import com.rr2222.personapi.dto.MessageResponseDTO;
import com.rr2222.personapi.dto.request.PersonDTO;
import com.rr2222.personapi.entity.Person;

import com.rr2222.personapi.exception.PersonNotFoundException;
import com.rr2222.personapi.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private PersonRepository personRepository;


    public MessageResponseDTO createPerson(PersonDTO personDTO){
        ModelMapper modelMapper = new ModelMapper();
        Person personToSave = modelMapper.map(personDTO, Person.class);
        personToSave.setBirthDate(convertStringToData(personDTO.getBirthDate()));
        Person savedPerson = personRepository.save(personToSave);
        return createMessageResponse("Created Person with ID ", savedPerson.getId());
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
        Optional<Person> retorno = verifyIfExists(id);
        ModelMapper modelMapper = new ModelMapper();
       return modelMapper.map(retorno.get(), PersonDTO.class);
    }


    public void deletePersonById(Long id) throws PersonNotFoundException {
        Optional<Person> retorno = verifyIfExists(id);
        if (!retorno.isPresent()){
            throw new PersonNotFoundException(id);
        }
        personRepository.delete(retorno.get());
    }

    public MessageResponseDTO updatePersonById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfExists(id);
        ModelMapper modelMapper = new ModelMapper();

        Person personToUpdate = modelMapper.map(personDTO, Person.class);
        personToUpdate.setBirthDate(convertStringToData(personDTO.getBirthDate()));

        Person updatedPerson = personRepository.save(personToUpdate);

        return createMessageResponse("Update Person with ID ", personToUpdate.getId());
    }

    private Optional<Person> verifyIfExists(Long id) throws PersonNotFoundException {
        Optional<Person> retorno = personRepository.findById(id);
        if (!retorno.isPresent()){
            throw new PersonNotFoundException(id);
        }
        return retorno;
    }

    private MessageResponseDTO createMessageResponse(String message, Long id) {
        return MessageResponseDTO.builder()
                .message(message + id)
                .build();
    }

    private LocalDate convertStringToData(String data){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(data, formatter);
        return localDate;
    }
}
