package com.immunisation.immunisationcode.ENTITIES;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class immunisationSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vaccineName;
    private String description;
    private LocalDate immunizationDate;


    @ManyToOne
    @JoinColumn(name = "siblingId")
    private Sibling sibling; // Assuming 'Sibling' is another entity
}
