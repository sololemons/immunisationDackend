package com.immunisation.immunisationcode.REPOSITORIES;

import com.immunisation.immunisationcode.ENTITIES.Sibling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface siblingRepository extends JpaRepository<Sibling, Integer> {
    List<Sibling> findByGuardianId(Integer Id);
}
