package com.rr2222.personapi.service;

import com.rr2222.personapi.dto.MessageResponseDTO;
import com.rr2222.personapi.dto.request.PersonDTO;
import com.rr2222.personapi.entity.Person;
import com.rr2222.personapi.repository.PersonRepository;
import com.rr2222.personapi.service.utils.PersonUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    void testGivenPersonDTOThenReturnSavedMessage(){
        // Given
        PersonDTO personDTOGiven = new PersonUtils().createFakeDTO();
        Person personEntityGiven = new PersonUtils().createFakeEntity();
        MessageResponseDTO expectedMessageResponse = MessageResponseDTO.builder()
                .message("Created Person with ID " + personEntityGiven.getId())
                .build();
        // When
        Mockito.when(personRepository.save(any(Person.class))).thenReturn(personEntityGiven);

        //Then
        MessageResponseDTO messageResponseDTO = personService.createPerson(personDTOGiven);
        Assertions.assertEquals(expectedMessageResponse, messageResponseDTO);
    }

}
