package com.rr2222.personapi.service.utils;

import com.rr2222.personapi.dto.request.PersonDTO;
import com.rr2222.personapi.dto.request.PhoneDTO;
import com.rr2222.personapi.entity.Person;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class PersonUtils {
    private String firstName = "Dio";
    private String lastName = "Brando";
    private String cpf = "000.000.000.00";
    private Long id = 1L;
    private LocalDate birthDate = LocalDate.of(1886, 04,07);



    public PersonDTO createFakeDTO(){
        PhoneUtils phoneUtils = new PhoneUtils();

        return PersonDTO.builder()
                .firstName(this.firstName)
                .lastName(this.lastName)
                .cpf(this.cpf)
                .birthDate("07-04-1886")
                .phones(Collections.singletonList(phoneUtils.createFakePhone()))
                .build();
    }

    public Person createFakeEntity(){
        PhoneUtils phoneUtils = new PhoneUtils();
        return Person.builder()
                .id(this.id)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .cpf(this.cpf)
                .birthDate(this.birthDate)
                .phones(Collections.singletonList(phoneUtils.createFakeEntity()))
                .build();
    }
}
