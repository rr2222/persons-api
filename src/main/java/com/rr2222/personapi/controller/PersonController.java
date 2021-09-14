package com.rr2222.personapi.controller;

import com.rr2222.personapi.dto.MessageResponseDTO;
import com.rr2222.personapi.entity.Person;
import com.rr2222.personapi.repository.PersonRepository;
import com.rr2222.personapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

    @Autowired
    private PersonService personService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody Person person){
        return personService.createPerson(person);
    }
}
