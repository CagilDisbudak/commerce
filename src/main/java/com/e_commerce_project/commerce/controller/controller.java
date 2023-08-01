package com.e_commerce_project.commerce.controller;
import com.e_commerce_project.commerce.model.Person;
import com.e_commerce_project.commerce.repository.PersonRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/updatePerson")
    public void updatePerson(@RequestBody Person person){
        Person existingPerson = personRepository.findById(person.id).orElse(null);
            if(person.name != null){
                existingPerson.setName(person.name);
            }
            if(person.password != null){
                existingPerson.setPassword(person.password);
            }
            if (existingPerson != null)
                personRepository.save(existingPerson);
    }


}


