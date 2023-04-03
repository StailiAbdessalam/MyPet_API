package com.mypet.back_end.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonResponse {
    private String referencePerson;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String city;
}
