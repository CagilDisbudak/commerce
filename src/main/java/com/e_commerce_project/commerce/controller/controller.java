package com.e_commerce_project.commerce.controller;

import com.e_commerce_project.commerce.model.Person;
import com.e_commerce_project.commerce.service.PersonService;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/person")
public class controller {
    private final PersonService personService;
    @Autowired
    public controller(PersonService personService){
        this.personService = personService;
    }

    @GetMapping("/index")
    public String index(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    @PostMapping("/register")
    public String addPerson(@RequestBody Person person){
        personService.addPerson(person);
        return "Done";
    }

    @JsonProperty
    @GetMapping("/get_person")
    public List<Person> getAllPeople(){
        return personService.getAllPeople();
    }

}
