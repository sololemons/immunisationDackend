package com.immunisation.immunisationcode.SERVICES;


import com.immunisation.immunisationcode.DTOS.SiblingDto;
import com.immunisation.immunisationcode.ENTITIES.Guardian;
import com.immunisation.immunisationcode.ENTITIES.Sibling;

import com.immunisation.immunisationcode.REPOSITORIES.siblingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class siblingServices {

    @Autowired
    private final siblingRepository siblingRepository;

    public siblingServices(siblingRepository siblingRepository) {
        this.siblingRepository = siblingRepository;
    }

    public List<Sibling> getAllSiblings() {
        return siblingRepository.findAll();
    }

    public Sibling addSibling(SiblingDto siblingDto, Guardian guardian) {
        Sibling sibling = new Sibling();
        sibling.setFirstName(siblingDto.getFirstName());
        sibling.setLastName(siblingDto.getLastName());
        sibling.setDateOfBirth(siblingDto.getDateOfBirth());
        sibling.setPlaceOfBirth(siblingDto.getPlaceOfBirth());
        sibling.setGuardian(guardian);
        return siblingRepository.save(sibling);
    }

    public Sibling updateSibling(SiblingDto siblingDto, Integer id) {
        Sibling sibling = siblingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sibling not found with ID: " + id));

        sibling.setFirstName(siblingDto.getFirstName());
        sibling.setLastName(siblingDto.getLastName());
        sibling.setDateOfBirth(siblingDto.getDateOfBirth());
        sibling.setPlaceOfBirth(siblingDto.getPlaceOfBirth());
        return siblingRepository.save(sibling);
    }

    public void deleteSibling(Integer id) {
        siblingRepository.deleteById(id);
    }

    public List<Sibling> getSiblingsByGuardianId(Integer Id) {
        return siblingRepository.findByGuardianId(Id);
    }
}
