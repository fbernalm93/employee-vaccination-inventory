package vaccinationinventory.vaccine.application;

import org.springframework.stereotype.Service;
import vaccinationinventory.vaccine.domain.entity.VaccinePerson;

@Service
public interface VaccinePersonInterface {
    public VaccinePerson getVaccinePerson(String id);

    public void saveVaccinePerson (VaccinePerson vaccinePerson);
}
