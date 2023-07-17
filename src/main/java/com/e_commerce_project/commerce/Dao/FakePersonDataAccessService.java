package com.e_commerce_project.commerce.Dao;

import com.e_commerce_project.commerce.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao{

    private static List<Person> DB = new ArrayList<>();
    public void insertPerson(UUID id, Person person){
        DB.add(new Person(id, person.getName()));
    }
    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }
}
