package com.rr2222.personapi.service;

import com.rr2222.personapi.dto.MessageResponseDTO;
import com.rr2222.personapi.entity.Person;
import com.rr2222.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public MessageResponseDTO createPerson(Person person){
        Person savedPerson = personRepository.save(person);
        return MessageResponseDTO.builder()
                .message("Created person with id" + savedPerson.getId())
                .build();
    }
}
