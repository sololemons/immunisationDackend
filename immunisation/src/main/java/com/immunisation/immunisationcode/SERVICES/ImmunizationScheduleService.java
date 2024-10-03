package com.immunisation.immunisationcode.SERVICES;

import com.immunisation.immunisationcode.ENTITIES.Sibling;
import com.immunisation.immunisationcode.ENTITIES.Vaccine;
import com.immunisation.immunisationcode.ENTITIES.immunisationSchedule;
import com.immunisation.immunisationcode.REPOSITORIES.ImmunizationScheduleRepository;
import com.immunisation.immunisationcode.REPOSITORIES.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImmunizationScheduleService {

    @Autowired
    private ImmunizationScheduleRepository immunizationScheduleRepository;

    @Autowired
    private VaccineRepository vaccineRepository;

    public void createScheduleForChild(Sibling sibling) {
        LocalDate birthDate = LocalDate.parse(sibling.getDateOfBirth(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        List<Vaccine> vaccines = vaccineRepository.findAll();
        List<immunisationSchedule> scheduleList = new ArrayList<>();

        for (Vaccine vaccine : vaccines) {
            LocalDate immunizationDate = birthDate
                    .plusWeeks(vaccine.getWeeksAfterBirth())
                    .plusMonths(vaccine.getMonthsAfterBirth())
                    .plusYears(vaccine.getYearsAfterBirth());

            immunisationSchedule schedule = new immunisationSchedule();
            schedule.setVaccineName(vaccine.getVaccineName());
            schedule.setDescription(vaccine.getDescription());
            schedule.setImmunizationDate(immunizationDate);
            schedule.setSibling(sibling);

            scheduleList.add(schedule);
        }

        System.out.println("Creating schedules: " + scheduleList.size());
        immunizationScheduleRepository.saveAll(scheduleList);
    }

    public List<immunisationSchedule> findBySiblingId(Long siblingId) {
        return immunizationScheduleRepository.findBySibling_SiblingId(siblingId);
    }
    public List<immunisationSchedule> findImmunizationsBetween(LocalDate startDate, LocalDate endDate) {
        return immunizationScheduleRepository.findByImmunizationDateBetween(startDate, endDate);
    }
}