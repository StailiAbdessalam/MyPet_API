package com.mypet.back_end.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Animals")
public class AnimalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "referenceanimal", nullable = false, unique = true)
    private String referenceAnimal;

    @Column
    private String name;

    @Column
    private String type;

    @Column
    private int age;

    @Column
    private String picture;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private PersonEntity person;

    @OneToOne(mappedBy = "animal")
    private PostEntity post;
}
