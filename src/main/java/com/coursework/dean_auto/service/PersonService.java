package com.coursework.dean_auto.service;

import com.coursework.dean_auto.entity.Person;

import java.util.List;
import java.util.Map;

public interface PersonService {
    Person savePerson(Person person);
    Person updatePerson(Person person);
    Person getPersonById(Integer id);
    List<Person> getAllPeople();
    List<Person> getPeopleByFirstName(String firstName);
    List<Person> getPeopleByLastName(String lastName);
    List<Person> getPeopleByFatherName(String fatherName);
    List<Person> getPeopleByNameAndSurname(String firstName, String lastName);
    List<Person> getPeopleByFullName(String firstName, String lastName, String fatherName);
    List<Person> getPeopleByGroupName(String groupName);
    Map<Integer, Person> getPeopleByMarkValue(int markValue);
    Map<Integer,Person> getPeopleBySubject(String subjectName);
    Map<Integer, Person> getPeopleByMarkAndSubject(Integer markValue, String subjectName);
    String deletePersonById(Integer id);
}
