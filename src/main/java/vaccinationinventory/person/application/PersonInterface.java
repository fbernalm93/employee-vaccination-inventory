package vaccinationinventory.person.application;

import org.springframework.stereotype.Service;
import vaccinationinventory.person.domain.entity.Person;
import vaccinationinventory.utils.exceptions.InvalidIdException;
import vaccinationinventory.utils.exceptions.PersonNotFoundException;

import java.util.List;
@Service
public interface PersonInterface {
    public void createEmployee(Person person) throws InvalidIdException;
    public void createAdministrator(Person person);
    public void updatePerson(Person person);
    public List<Person> listAllEmployees();
    public Person findEmployeeById(String id);
    public void deletePerson(String id)throws PersonNotFoundException;
    public void updateEmploye(String id, Person person) throws PersonNotFoundException;
    public void updateEmployeVaccines(String id, Person person) throws PersonNotFoundException;
}
