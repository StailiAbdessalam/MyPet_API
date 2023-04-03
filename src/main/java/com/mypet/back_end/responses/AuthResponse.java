package com.mypet.back_end.responses;

import com.mypet.back_end.dto.PersonDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {
    private String token;
    private PersonResponse personResponse;
}
