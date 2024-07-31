package com.immunisation.immunisationcode.REPOSITORIES;

import com.immunisation.immunisationcode.ENTITIES.Guardian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuardianRepository extends JpaRepository<Guardian, Integer> {
    Optional<Guardian> findByEmail(String email);

}
