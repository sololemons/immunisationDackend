package com.immunisation.immunisationcode.CONTROLLER;



import com.immunisation.immunisationcode.DTOS.SiblingDto;
import com.immunisation.immunisationcode.ENTITIES.Guardian;
import com.immunisation.immunisationcode.ENTITIES.Sibling;
import com.immunisation.immunisationcode.REPOSITORIES.GuardianRepository;
import com.immunisation.immunisationcode.SERVICES.ImmunizationScheduleService;
import com.immunisation.immunisationcode.SERVICES.siblingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/siblings")
public class siblingController {

    @Autowired
    private siblingServices siblingService;


    @Autowired
    private GuardianRepository guardianRepository;
    @Autowired
    private ImmunizationScheduleService immunizationScheduleService;
    @Autowired
    private com.immunisation.immunisationcode.REPOSITORIES.siblingRepository siblingRepository;


    @GetMapping("/get")
    public List<Sibling> getAllSiblings() {
        return siblingService.getAllSiblings();
    }

    @GetMapping("get/guardian/{guardianId}")
    public List<Sibling> getSiblingsByGuardianId(@PathVariable Integer guardianId) {
        return siblingService.getSiblingsByGuardianId(guardianId);

    }

    @PostMapping("/add")
    public ResponseEntity<SiblingDto> addNewSibling(@RequestBody SiblingDto siblingDto) {
        Optional<Guardian> guardian = guardianRepository.findById(siblingDto.getGuardianId());
        if (guardian.isPresent()) {
             Sibling siblings = siblingService.addSibling(siblingDto, guardian.get());
            immunizationScheduleService.createScheduleForChild(siblings);

            return ResponseEntity.ok(siblingDto);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/update/{siblingId}")
    public ResponseEntity<SiblingDto> updateSibling(@RequestBody SiblingDto siblingDto, @PathVariable Integer siblingId) {
        // Ensure the sibling exists first
        Sibling sibling = siblingRepository.findById(siblingId)
                .orElseThrow(() -> new RuntimeException("Sibling not found with ID: " + siblingId));

        // Check if the guardian exists
        Optional<Guardian> guardian = guardianRepository.findById(siblingDto.getGuardianId());
        if (guardian.isPresent()) {
            // Proceed with update if the guardian exists
            siblingService.updateSibling(siblingDto, siblingId);
            return ResponseEntity.ok(siblingDto);
        } else {
            return ResponseEntity.notFound().build(); // Return 404 if guardian doesn't exist
        }
    }


    @DeleteMapping("/delete/{siblingId}")
    public ResponseEntity<String> deleteSibling(@PathVariable Integer siblingId) {
        siblingService.deleteSibling(siblingId);
        return ResponseEntity.ok("Sibling Deleted Successfully");
    }
}

