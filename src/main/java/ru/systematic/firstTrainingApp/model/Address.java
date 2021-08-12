package ru.systematic.firstTrainingApp.model;

import lombok.Data;

@Data
public class Address {

    private String country;
    private String city;
    private String street;
    private int house;
    private Integer apartment;
}
