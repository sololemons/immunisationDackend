package com.immunisation.immunisationcode.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class SiblingDto {
    private String firstName;
    private String lastName;
    private String placeOfBirth;
    private String dateOfBirth;
    private int guardianId; // Reference to the Guardian
}
