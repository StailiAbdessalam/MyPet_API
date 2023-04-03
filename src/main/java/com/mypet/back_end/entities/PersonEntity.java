package com.mypet.back_end.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Persons")
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "referenceperson", nullable = false, unique = true)
    private String referencePerson;

    @Column(name = "firstname", nullable = false)
    private String firstName;

    @Column(name = "lastname", nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @OneToMany(mappedBy = "person")
    private List<PostEntity> myPosts = new ArrayList<PostEntity>();

    @OneToMany(mappedBy = "person")
    private List<AnimalEntity> myAnimals = new ArrayList<AnimalEntity>();

    @OneToMany(mappedBy = "person")
    private List<AdoptionContractEntity> myAdoptionContracts = new ArrayList<AdoptionContractEntity>();

    @OneToMany(mappedBy = "person")
    private List<CommentEntity> myComments = new ArrayList<CommentEntity>();

    @OneToMany(mappedBy = "person")
    private List<CommentResponseEntity> myCommentResponses = new ArrayList<CommentResponseEntity>();
}
