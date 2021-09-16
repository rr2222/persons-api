package com.rr2222.personapi.controller;

import com.rr2222.personapi.dto.MessageResponseDTO;
import com.rr2222.personapi.dto.request.PersonDTO;
import com.rr2222.personapi.entity.Person;
import com.rr2222.personapi.exception.PersonNotFoundException;
import com.rr2222.personapi.repository.PersonRepository;
import com.rr2222.personapi.service.PersonService;
import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/people")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {

    private PersonService personService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO){
        return personService.createPerson(personDTO);
    }

    @GetMapping
    public List<PersonDTO> listAllPersons(){
        return personService.listAll();
    }

    @GetMapping("/{id}")
    public PersonDTO findPersonById(@PathVariable Long id) throws PersonNotFoundException {
        return personService.findById(id);
    }

    @PutMapping("/{id}")
    public MessageResponseDTO updateById(@PathVariable  Long id, @RequestBody PersonDTO personDTO) throws PersonNotFoundException {
        return personService.updatePersonById(id, personDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws PersonNotFoundException {
        personService.deletePersonById(id);
    }
}
