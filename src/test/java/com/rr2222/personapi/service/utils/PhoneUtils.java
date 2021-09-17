package com.rr2222.personapi.service.utils;

import com.rr2222.personapi.dto.request.PhoneDTO;
import com.rr2222.personapi.entity.Phone;
import com.rr2222.personapi.enums.PhoneType;

public class PhoneUtils {

    private PhoneType phoneType = PhoneType.HOME;
    private String number = "(11)999999999";
    private Long id = 1L;
    public PhoneDTO createFakePhone(){
        return PhoneDTO.builder()
                .type(this.phoneType)
                .number(this.number)
                .build();
    }

    public Phone createFakeEntity(){
        return Phone.builder()
                .id(this.id)
                .type(this.phoneType)
                .number(this.number)
                .build();
    }
}
