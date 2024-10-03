package com.immunisation.immunisationcode.ENTITIES;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Siblings")
@Data
public class Sibling {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("siblingId")
    @Column( name = "siblingId")
    private Long siblingId;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name ="dateOfBirth")
    private String dateOfBirth;
    @Column(name = "placeOfBirth")
    private String placeOfBirth;

    @ManyToOne
    @JoinColumn(name = "guardianId", nullable = false)
    private Guardian guardian;

    public Sibling() {
    }

    @Override
    public String toString() {
        return "Sibling{" +
                "siblingId=" + siblingId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", placeOfBirth='" + placeOfBirth + '\'' +
                ", guardian=" + guardian +
                '}';
    }
}
