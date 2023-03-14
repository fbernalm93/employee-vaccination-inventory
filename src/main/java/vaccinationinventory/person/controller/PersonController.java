//@Path("/product")
//@Produces(MediaType.APPLICATION_JSON)

package vaccinationinventory.person.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vaccinationinventory.person.application.PersonService;
import vaccinationinventory.person.domain.entity.Person;
import vaccinationinventory.utils.exceptions.InvalidIdException;
import vaccinationinventory.utils.exceptions.PersonNotFoundException;

import javax.validation.Valid;

import java.util.List;

import static vaccinationinventory.utils.Validations.formatMessage;

@RestController
@RequestMapping("/person")
public class PersonController{
    @Autowired
    private PersonService personService;
    private static Logger LOG = LoggerFactory.getLogger(PersonController.class);
    @PostMapping("/newemployee")
//    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    @ApiOperation(value = "Create a new person with Employee Role in database and a new user with required fields " +
            "(id, " + "name, lastname,email),created user has username=person id and default password= person id," +
            "(Role administrador required)")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Person created"),
            @ApiResponse(code = 400, message = "Validation error in object")})
    public ResponseEntity<Boolean> newEmployee(@Valid @RequestBody Person person, BindingResult result) {
        LOG.info("Initializing - New employee create process");
        ResponseEntity response;
        if(result.hasErrors()){
            response = new ResponseEntity(formatMessage(result),HttpStatus.BAD_REQUEST);
        }else{
            try {
                personService.createEmployee(person);
                response = new ResponseEntity("New Employee created!", HttpStatus.CREATED);
            } catch (InvalidIdException e) {
                response = new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
            }catch (Exception er){
                response = new ResponseEntity(er.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return response;
    }

    @PatchMapping("/updateemployee/{idperson}")
    @ApiOperation(value = "Update person in database. You can change any field like as" +
            "(address, birthdate, phonenumber, name, lastname, all fields or just one)")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Person updated"),
            @ApiResponse(code = 400, message = "Validation error in object")})
    public ResponseEntity<Boolean> updateEmployee(@PathVariable ("idperson") String idPerson, @RequestBody Person person) {
        LOG.info("Initializing - Update employee create process");
        ResponseEntity response;
        try{
            personService.updateEmploye(idPerson, person);
            response = new ResponseEntity("Employee updated!", HttpStatus.OK);
        }catch (PersonNotFoundException e){
            response = new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception er){
            response = new ResponseEntity(er.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @DeleteMapping("/deleteemployee/{idperson}")
    @ApiOperation(value = "Delete person with Employee Role in database by Id Person )" +
            "(Role administrador required)")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Person deleted"),
            @ApiResponse(code = 400, message = "Validation error in object")})
    public ResponseEntity<Boolean> deleteEmployee(@PathVariable ("idperson") String idPerson) {
        LOG.info("Initializing - Delete employee create process");
        ResponseEntity response;
        try{
            personService.deletePerson(idPerson);
            response = new ResponseEntity("Employee deleted!", HttpStatus.OK);
        }catch (PersonNotFoundException e){
            response = new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception er){
            response = new ResponseEntity(er.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @GetMapping("/listall")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    @ApiOperation(value = "Get all persons on database - Role administrador required")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "List of persons") })
    public ResponseEntity<List<Person>> listAll() {
        return new ResponseEntity<>(personService.listAllEmployees(), HttpStatus.OK);
    }

    @PatchMapping("/setvacunas/{idperson}")
    @ApiOperation(value = "Update person in database. You can change any field like as" +
            "(address, birthdate, phonenumber, name, lastname, all fields or just one)")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Person updated"),
            @ApiResponse(code = 400, message = "Validation error in object")})
    public ResponseEntity<Boolean> updateEmployeeVaccines(@PathVariable ("idperson") String idPerson, @RequestBody Person person) {
        LOG.info("Initializing - Update employee create process");
        ResponseEntity response;
        try{
            if(person.getIsVaccinated()){
                personService.updateEmployeVaccines(idPerson,person);
                response = new ResponseEntity("Employee updated with Vaccines!", HttpStatus.OK);
            }else {
                personService.updateEmploye(idPerson, person);
                response = new ResponseEntity("Employee updated!", HttpStatus.OK);
            }
        }catch (PersonNotFoundException e){
            response = new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception er){
            response = new ResponseEntity(er.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
}
