package com.immunisation.immunisationcode.REPOSITORIES;


import com.immunisation.immunisationcode.ENTITIES.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Long> {

}
