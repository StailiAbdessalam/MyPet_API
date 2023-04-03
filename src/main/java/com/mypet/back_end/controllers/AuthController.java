package com.mypet.back_end.controllers;

import com.mypet.back_end.dto.PersonDto;
import com.mypet.back_end.helpers.JwtUtils;
import com.mypet.back_end.requests.AuthRequest;
import com.mypet.back_end.requests.PersonRequest;
import com.mypet.back_end.responses.AuthResponse;
import com.mypet.back_end.responses.PersonResponse;
import com.mypet.back_end.services.PersonService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@CrossOrigin("*")
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    private PersonService personService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    public UserDetails userDetails;

    private void authenticationManager(String email, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
    }


    @PostMapping("/register")
    public ResponseEntity<PersonResponse> register(@RequestBody PersonRequest personRequest) {
        PersonDto personDto = new PersonDto();
        BeanUtils.copyProperties(personRequest, personDto);
        PersonDto createdPerson = personService.addPerson(personDto);
        PersonResponse personResponse = new PersonResponse();
        BeanUtils.copyProperties(createdPerson, personResponse);
        return new ResponseEntity<PersonResponse>(personResponse, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        PersonDto personDto = new PersonDto();
        BeanUtils.copyProperties(authRequest, personDto);
        PersonDto loggedInPerson = personService.login(personDto);
        PersonResponse personResponse = new PersonResponse();
        AuthResponse authResponse = new AuthResponse();
        BeanUtils.copyProperties(loggedInPerson, personResponse);
        authResponse.setPersonResponse(personResponse);


        if (authResponse != null) {
            authenticationManager(personDto.getEmail(), personDto.getPassword());
            final UserDetails userDetails = new User(personDto.getEmail(), personDto.getPassword(), Collections.singleton(new SimpleGrantedAuthority(personDto.getRole())));
            authResponse.setToken(jwtUtils.generateToken(userDetails));
            return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }




}
