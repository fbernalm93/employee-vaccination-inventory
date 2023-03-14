package vaccinationinventory.vaccine.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vaccinationinventory.vaccine.domain.entity.VaccinePerson;
@Repository
public interface VaccinePersonRepository extends JpaRepository<VaccinePerson,String> {

}
