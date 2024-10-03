package com.immunisation.immunisationcode.CONTROLLER;


import com.immunisation.immunisationcode.ENTITIES.Guardian;
import com.immunisation.immunisationcode.SERVICES.GuardianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
    @RequestMapping("/api/guardian")
    public class GuardianController {

        @Autowired
        private GuardianService guardianService;

        // Get guardian details by ID
        @GetMapping("/{Id}")
        public ResponseEntity<Guardian> getGuardianById(@PathVariable Integer Id) {
            Guardian guardian = guardianService.findGuardianById(Id);
            if (guardian != null) {
                return ResponseEntity.ok(guardian); // Return HTTP 200 OK with the guardian data
            } else {
                return ResponseEntity.notFound().build(); // Return HTTP 404 if guardian not found
            }
        }
    }


