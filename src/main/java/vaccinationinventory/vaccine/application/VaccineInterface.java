package vaccinationinventory.vaccine.application;

import org.springframework.stereotype.Service;
import vaccinationinventory.vaccine.domain.entity.Vaccine;
@Service
public interface VaccineInterface {
    public Vaccine getVaccine(String name);
}
