package vaccinationinventory.vaccine.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vaccinationinventory.vaccine.domain.entity.VaccinePerson;
import vaccinationinventory.vaccine.domain.repository.VaccinePersonRepository;

@Service
public class VaccinePersonService implements VaccinePersonInterface{
    @Autowired
    VaccinePersonRepository vaccinePersonRepository;

    @Override
    public VaccinePerson getVaccinePerson(String id) {
        return vaccinePersonRepository.findById(id).orElse(null);
    }

    @Override
    public void saveVaccinePerson(VaccinePerson vaccinePerson) {
        vaccinePersonRepository.save(vaccinePerson);
    }
}
