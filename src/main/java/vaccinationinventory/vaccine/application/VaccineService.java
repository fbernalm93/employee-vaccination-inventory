package vaccinationinventory.vaccine.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vaccinationinventory.vaccine.domain.entity.Vaccine;
import vaccinationinventory.vaccine.domain.repository.VaccineRepositoryInterface;
@Service
public class VaccineService implements VaccineInterface{
    @Autowired
    VaccineRepositoryInterface vaccineRepository;
    @Override
    public Vaccine getVaccine(String name) {
        return vaccineRepository.findByName(name);
    }
}
