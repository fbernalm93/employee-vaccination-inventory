package vaccinationinventory.person.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vaccinationinventory.person.domain.entity.Person;
import vaccinationinventory.person.domain.repository.PersonRepositoryInterface;
import vaccinationinventory.user.domain.entity.Role;
import vaccinationinventory.user.domain.entity.UserApp;
import vaccinationinventory.user.domain.repository.UserRepositoryInterface;
import vaccinationinventory.utils.Validations;
import vaccinationinventory.utils.exceptions.InvalidIdException;
import vaccinationinventory.utils.exceptions.PersonNotFoundException;
import vaccinationinventory.vaccine.application.VaccinePersonService;
import vaccinationinventory.vaccine.application.VaccineService;
import vaccinationinventory.vaccine.domain.entity.VaccinePerson;

import java.util.List;

@Service
public class PersonService implements PersonInterface{
    @Autowired
    private PersonRepositoryInterface personRepository;
    @Autowired
    private UserRepositoryInterface userRepository;
    @Autowired
    private VaccinePersonService vaccinePersonService;
    @Autowired
    private VaccineService vaccineService;
    private Validations validationsUtils;
    private static Logger LOG = LoggerFactory.getLogger(PersonService.class);
    private BCryptPasswordEncoder encoder;
    @Override
    public void createEmployee(Person person) throws InvalidIdException {
        //Context validations
        if (!validationsUtils.isValidEcuadorianId(person.getId())) throw new InvalidIdException("Identification not valid - Ecuadorian Id");
        LOG.info("The Employee validations are OK");
        personRepository.save(person);
        UserApp user = new UserApp();
        user.setUsername(person.getId());
        //Set encrypt password
        user.setPassword(encoder.encode(person.getId()));
        user.setRole(Role.EMPLOYEE);
        userRepository.save(user);
    }

    @Override
    public void createAdministrator(Person person) {
        personRepository.save(person);
        UserApp user = new UserApp();
        user.setUsername(person.getId());
        //Set encrypt password
        user.setPassword(encoder.encode(person.getId()));
        user.setRole(Role.ADMINISTRATOR);
        userRepository.save(user);
    }
    @Override
    public void updateEmploye(String id, Person person) throws PersonNotFoundException{
        Person searchPerson = findEmployeeById(id);
        if (searchPerson != null){
            searchPerson.copyNotNullData(person);
            LOG.info("Employee updated");
            updatePerson(searchPerson);
        }else {
            throw new PersonNotFoundException("Employee not found");
        }
    }
    @Override
    public void updateEmployeVaccines(String id, Person person) throws PersonNotFoundException{
        Person searchPerson = findEmployeeById(id);
        if (searchPerson != null){
            searchPerson.copyNotNullData(person);
            LOG.info("Employee updated");
            updatePerson(searchPerson);
            VaccinePerson vaccinePerson = vaccinePersonService.getVaccinePerson(searchPerson.getId());
            if(vaccinePerson==null){
                vaccinePerson = new VaccinePerson(searchPerson.getId(),vaccineService.getVaccine(person.getVaccine().getName()).getId(),person.getVaccine().getVaccineDoses(),person.getVaccine().getDate());
                vaccinePersonService.saveVaccinePerson(vaccinePerson);
            }
        }else {
            throw new PersonNotFoundException("Employee not found");
        }
    }
    @Override
    public void updatePerson(Person person) {
        personRepository.save(person);
    }

    @Override
    public List<Person> listAllEmployees() {
        return personRepository.findAll();
    }

    @Override
    public Person findEmployeeById(String id) {
        return personRepository.findById(id).orElse(null);
    }

    @Override
    public void deletePerson(String id) throws PersonNotFoundException {
        Person person = personRepository.findById(id).orElse(null);
        if (person != null){
            LOG.info("The Employee deleted");
            personRepository.delete(person);
            UserApp user = userRepository.findById(id).orElse(null);
            userRepository.delete(user);
        }else {
            throw new PersonNotFoundException("Employee not found");
        }
    }
}