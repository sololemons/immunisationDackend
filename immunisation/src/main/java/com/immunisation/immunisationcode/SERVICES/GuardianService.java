package com.immunisation.immunisationcode.SERVICES;

import com.immunisation.immunisationcode.ENTITIES.Guardian;
import com.immunisation.immunisationcode.REPOSITORIES.GuardianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuardianService {

    @Autowired
    private GuardianRepository guardianRepository;

    public Guardian findGuardianById(Integer Id) {
        return guardianRepository.findById(Id).orElse(null); // Fetch guardian by ID
    }
}
