package vaccinationinventory.vaccine.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "vaccine")
public class Vaccine {
    @Id
    private Integer id;
    private String name;
}
