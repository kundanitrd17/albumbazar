package com.albumbazaar.albumbazar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "frequent_question")
public class FrequentQuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "question", columnDefinition = "text")
    private String question;

    @Column(name = "answer", columnDefinition = "text")
    private String answer;

}
