package com.immunisation.immunisationcode.ENTITIES;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Vaccine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vaccineName;
    private String description;
    private int weeksAfterBirth;
    private int monthsAfterBirth;
    private int yearsAfterBirth;

    // Parameterized constructor
    public Vaccine(String vaccineName, String description, int weeksAfterBirth, int monthsAfterBirth, int yearsAfterBirth) {
        this.vaccineName = vaccineName;
        this.description = description;
        this.weeksAfterBirth = weeksAfterBirth;
        this.monthsAfterBirth = monthsAfterBirth;
        this.yearsAfterBirth = yearsAfterBirth;
    }

    // No-args constructor
    public Vaccine() {
    }
}
