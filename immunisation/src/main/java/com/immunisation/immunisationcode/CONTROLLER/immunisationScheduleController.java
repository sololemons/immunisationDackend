package com.immunisation.immunisationcode.CONTROLLER;

import com.immunisation.immunisationcode.ENTITIES.immunisationSchedule;
import com.immunisation.immunisationcode.SERVICES.ImmunizationScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api")
public class immunisationScheduleController {


    private final ImmunizationScheduleService immunizationScheduleService;

@Autowired
    public immunisationScheduleController(ImmunizationScheduleService immunizationScheduleService) {
        this.immunizationScheduleService = immunizationScheduleService;
    }
    @GetMapping("/schedule/{siblingId}")
    public ResponseEntity<List<immunisationSchedule>> getImmunizationScheduleBySiblingId(@PathVariable Long siblingId) {
        List<immunisationSchedule> schedules = immunizationScheduleService.findBySiblingId(siblingId);
        if (schedules.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(schedules);
    }
    @GetMapping("/upcoming")
    public List<immunisationSchedule> getUpcomingSchedules(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return immunizationScheduleService.findImmunizationsBetween(startDate, endDate);
    }
}
