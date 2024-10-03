package com.immunisation.immunisationcode.SERVICES;

import com.immunisation.immunisationcode.ENTITIES.Guardian;
import com.immunisation.immunisationcode.ENTITIES.Sibling;
import com.immunisation.immunisationcode.ENTITIES.immunisationSchedule;
import com.immunisation.immunisationcode.REPOSITORIES.GuardianRepository;
import com.immunisation.immunisationcode.REPOSITORIES.ImmunizationScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ImmunizationReminderService {

    @Autowired
    private ImmunizationScheduleRepository immunizationRepository; // Your repository to fetch immunization data

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private GuardianRepository guardianRepository; // Your repository to fetch guardian info

    // This method runs every day at 8 AM
    @Scheduled(cron = "0 0 8 * * *") // 8 AM daily
    public void sendDailyImmunizationReminders() {
        LocalDate today = LocalDate.now();
        LocalDate fiveDaysLater = today.plus(5, ChronoUnit.DAYS);

        // Fetch immunizations due within the next 5 days
        List<immunisationSchedule> upcomingImmunizations = immunizationRepository
                .findByImmunizationDateBetween(today, fiveDaysLater);

        for (immunisationSchedule immunization : upcomingImmunizations) {
            Sibling sibling = immunization.getSibling();
            Guardian guardian = sibling.getGuardian();

            String subject = "Upcoming Immunization Reminder for " + sibling.getFirstName();
            String body = String.format("Dear %s, \n\nThis is a reminder that %s's immunization for the %s vaccine is due on %s. Please make sure to take them to the clinic.",
                    guardian.getFirstName(), sibling.getFirstName(), immunization.getVaccineName(), immunization.getImmunizationDate());

            // Send the email every day until the immunization date
            if (today.isBefore(immunization.getImmunizationDate()) || today.equals(immunization.getImmunizationDate())) {
                emailSenderService.sendSimpleEmail(guardian.getEmail(), subject, body);
            }
        }
    }
}
