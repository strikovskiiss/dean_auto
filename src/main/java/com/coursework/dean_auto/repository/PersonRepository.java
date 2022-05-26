package com.coursework.dean_auto.repository;

import com.coursework.dean_auto.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    List<Person> findPeopleByFirstName(String firstName);
    List<Person> findPeopleByLastName(String lastName);
    List<Person> findPeopleByFatherName(String fatherName);
    List<Person> findPeopleByFirstNameAndLastName(String firstName, String lastName);
    List<Person> findPeopleByFirstNameAndLastNameAndFatherName(String firstName, String lastName, String fatherName);
    List<Person> findPeopleByGroupName(String groupName);
}
