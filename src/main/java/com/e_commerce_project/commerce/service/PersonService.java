package com.e_commerce_project.commerce.service;
import com.e_commerce_project.commerce.Dao.PersonDao;
import com.e_commerce_project.commerce.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private final PersonDao personDao;

    @Autowired
    public PersonService(@Qualifier("fakeDao") PersonDao personDao){
        this.personDao = personDao;
    }
    public void addPerson(Person person){
        personDao.insertPerson(person);
    }
    public List<Person> getAllPeople(){
        return personDao.selectAllPeople();
    }
}
