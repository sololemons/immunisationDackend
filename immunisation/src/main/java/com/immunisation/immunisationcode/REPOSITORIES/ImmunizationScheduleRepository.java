package com.immunisation.immunisationcode.REPOSITORIES;

import com.immunisation.immunisationcode.ENTITIES.immunisationSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ImmunizationScheduleRepository extends JpaRepository<immunisationSchedule, Long> {

    List<immunisationSchedule> findBySibling_SiblingId(Long siblingId);
    List<immunisationSchedule> findByImmunizationDateBetween(LocalDate startDate, LocalDate endDate);
}
