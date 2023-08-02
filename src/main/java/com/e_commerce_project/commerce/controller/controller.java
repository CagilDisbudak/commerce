package com.e_commerce_project.commerce.controller;
import com.e_commerce_project.commerce.error.PersonErrorResponse;
import com.e_commerce_project.commerce.error.PersonNotFoundException;
import com.e_commerce_project.commerce.model.Person;
import com.e_commerce_project.commerce.repository.PersonRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/person")
public class controller {

    private final PersonRepository personRepository;
    @Autowired
    public controller(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    @GetMapping("/index")
    public String index(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    @PostMapping("/register")
    public Person addPerson(@RequestBody Person person){
        return personRepository.save(person);
    }

    @JsonProperty
    @GetMapping("/getPerson")
    public List<Person> getAllPeople(){
        return  personRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public void deletePeople(@PathVariable UUID id ){personRepository.deleteById(id);}

    @PatchMapping("/updatePerson")
    public void updatePartPerson(@RequestBody Person person){
        Person existingPerson = personRepository.findById(person.id).orElse(null);
            if (existingPerson != null){
                if(person.name != null){
                    existingPerson.setName(person.name);
                }
                if(person.password != null){
                    existingPerson.setPassword(person.password);
                }
                personRepository.save(existingPerson);
            }
            else throw new PersonNotFoundException("Person can not found! " + person.id);

    }
    @PostMapping("/updatePerson")
    public void updatePerson(@Valid @RequestBody Person person){
        Person existingPerson = personRepository.findById(person.id).orElse(null);
        if (existingPerson != null){
            personRepository.save(existingPerson);
        }
        else throw new PersonNotFoundException("Person can not found! " + person.id);
    }

    @ExceptionHandler
    public ResponseEntity<PersonErrorResponse> handleException(PersonNotFoundException exc){
        PersonErrorResponse error = new PersonErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMassage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

}


