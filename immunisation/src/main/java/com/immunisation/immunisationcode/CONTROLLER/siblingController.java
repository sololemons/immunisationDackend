package com.immunisation.immunisationcode.CONTROLLER;



import com.immunisation.immunisationcode.DTOS.SiblingDto;
import com.immunisation.immunisationcode.ENTITIES.Guardian;
import com.immunisation.immunisationcode.ENTITIES.Sibling;
import com.immunisation.immunisationcode.REPOSITORIES.GuardianRepository;
import com.immunisation.immunisationcode.SERVICES.siblingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("siblings")
public class siblingController {

    @Autowired
    private siblingServices siblingService;

    @Autowired
    private GuardianRepository guardianRepository;

    @GetMapping("/get")
    public List<Sibling> getAllSiblings() {
        return siblingService.getAllSiblings();
    }

    @GetMapping("/guardian/{Id}")
    public List<Sibling> getSiblingsByGuardianId(@PathVariable Integer Id) {
        return siblingService.getSiblingsByGuardianId(Id);
    }

    @PostMapping("/add")
    public ResponseEntity<SiblingDto> addNewSibling(@RequestBody SiblingDto siblingDto) {
        Optional<Guardian> guardian = guardianRepository.findById(siblingDto.getGuardianId());
        if (guardian.isPresent()) {
            siblingService.addSibling(siblingDto, guardian.get());
            return ResponseEntity.ok(siblingDto);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/update/{siblingId}")
    public ResponseEntity<SiblingDto> updateSibling(@RequestBody SiblingDto siblingDto, @PathVariable Integer siblingId) {
        Optional<Guardian> guardian = guardianRepository.findById(siblingDto.getGuardianId());
        if (guardian.isPresent()) {
            siblingService.updateSibling(siblingDto, siblingId);
            return ResponseEntity.ok(siblingDto);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{siblingId}")
    public ResponseEntity<String> deleteSibling(@PathVariable Integer siblingId) {
        siblingService.deleteSibling(siblingId);
        return ResponseEntity.ok("Sibling Deleted Successfully");
    }
}

