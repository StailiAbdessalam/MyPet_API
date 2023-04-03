package com.mypet.back_end.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "CommentResponses")
public class CommentResponseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String content;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private PersonEntity person;


    @ManyToOne
    @JoinColumn(name = "comment_id")
    private CommentEntity comment;
}
