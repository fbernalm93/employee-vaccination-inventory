package vaccinationinventory.vaccine.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vaccinationinventory.vaccine.domain.entity.Vaccine;
@Repository
public interface VaccineRepositoryInterface extends JpaRepository<Vaccine,String> {
    public Vaccine findByName (String name);
}
