package com.mypet.back_end.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Posts")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "referencepost", nullable = false, unique = true)
    private String referencePost;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private int days;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private PersonEntity person;

    @OneToOne
    @JoinColumn(name = "animal_id")
    private AnimalEntity animal;

    @OneToMany(mappedBy = "post")
    private List<CommentEntity> myComments = new ArrayList<CommentEntity>();

    @OneToOne(mappedBy = "post")
    private AdoptionContractEntity adoptionContract;

}
