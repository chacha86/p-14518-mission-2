package com.example.demo.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class WiseSaying {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String saying;
    private String author;

}
