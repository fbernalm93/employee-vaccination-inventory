package vaccinationinventory.vaccine.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vaccinePerson")
public class VaccinePerson {
    @Id
    private String idPerson;
    private Integer idVaccine;
    private Integer vaccineDoses;
    private Date date;
}
