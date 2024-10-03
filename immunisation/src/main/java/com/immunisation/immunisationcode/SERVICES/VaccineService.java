package com.immunisation.immunisationcode.SERVICES;


import com.immunisation.immunisationcode.ENTITIES.Vaccine;
import com.immunisation.immunisationcode.REPOSITORIES.VaccineRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaccineService {

    @Autowired
    private VaccineRepository vaccineRepository;

    @PostConstruct
    @Transactional
    public void loadVaccineData() {
        vaccineRepository.deleteAll();
        List<Vaccine> vaccines = List.of(
                new Vaccine("BCG", "Bacille Calmette-Guérin vaccine", 0, 0, 0),
                new Vaccine("OPV", "Oral Polio Vaccine", 6, 0, 0),
                new Vaccine("OPV", "Oral Polio Vaccine", 10, 0, 0),
                new Vaccine("OPV", "Oral Polio Vaccine", 14, 0, 0),
                new Vaccine("Rotavirus", "Rotavirus vaccine", 6, 0, 0),
                new Vaccine("Rotavirus", "Rotavirus vaccine", 10, 0, 0),
                new Vaccine("Pneumo_conj", "Pneumococcal conjugate vaccine", 6, 0, 0),
                new Vaccine("Pneumo_conj", "Pneumococcal conjugate vaccine", 10, 0, 0),
                new Vaccine("Pneumo_conj", "Pneumococcal conjugate vaccine", 14, 0, 0),
                new Vaccine("DTwPHibHepB", "Diphtheria, Tetanus, Pertussis, Hib, Hepatitis B vaccine", 6, 0, 0),
                new Vaccine("DTwPHibHepB", "Diphtheria, Tetanus, Pertussis, Hib, Hepatitis B vaccine", 10, 0, 0),
                new Vaccine("DTwPHibHepB", "Diphtheria, Tetanus, Pertussis, Hib, Hepatitis B vaccine", 14, 0, 0),
                new Vaccine("IPV", "Inactivated Polio Vaccine", 14, 0, 0),
                new Vaccine("YF", "Yellow Fever vaccine", 0, 9, 0),
                new Vaccine("Measles", "Measles vaccine", 0, 9, 0),
                new Vaccine("Measles", "Measles vaccine", 0, 18, 0),
                new Vaccine("HPV", "Human Papillomavirus vaccine", 0, 0, 10),
                new Vaccine("HPV", "Human Papillomavirus vaccine (2nd dose)", 0, 6, 10),
                new Vaccine("HepB_Adult", "Hepatitis B Adult Dose vaccine", 0, 0, 0),
                new Vaccine("HepB_Adult", "Hepatitis B Adult Dose vaccine (2nd dose)", 0, 1, 0),
                new Vaccine("HepB_Adult", "Hepatitis B Adult Dose vaccine (3rd dose)", 0, 2, 0),
                new Vaccine("TT", "Tetanus Toxoid vaccine", 0, 0, 0),
                new Vaccine("TT", "Tetanus Toxoid vaccine (2nd dose)", 0, 1, 0),
                new Vaccine("TT", "Tetanus Toxoid vaccine (3rd dose)", 0, 6, 0)
        );

        vaccineRepository.saveAll(vaccines);
    }
}
