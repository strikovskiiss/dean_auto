package com.coursework.dean_auto.service;

import com.coursework.dean_auto.entity.Mark;
import com.coursework.dean_auto.entity.Person;
import com.coursework.dean_auto.exception.EntityException;
import com.coursework.dean_auto.exception.WrongEntityParameterException;
import com.coursework.dean_auto.repository.MarkRepository;
import com.coursework.dean_auto.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService{

    @Autowired
    PersonRepository personRepository;

    @Autowired
    MarkRepository markRepository;

    @Override
    public Person savePerson(Person person) {
        checkInfo(person);
        return personRepository.save(person);
    }

    @Override
    public Person updatePerson(Person person) {
        checkInfo(person);
        return personRepository.save(person);
    }

    @Override
    public Person getPersonById(Integer id) {
        Optional<Person> personOptional = personRepository.findById(id);
        if (personOptional.isEmpty()){
            throw new EntityException("There is no person in the database with id: " + id);
        }
        return personOptional.get();
    }

    @Override
    public List<Person> getAllPeople() {
        List<Person> people = personRepository.findAll();
        if (people.isEmpty()){
            throw new EntityException("There are no people in this database!");
        }
        return people;
    }

    @Override
    public List<Person> getPeopleByFirstName(String firstName) {
        List<Person> people = personRepository.findPeopleByFirstName(firstName);
        if (people.isEmpty()){
            throw new EntityException("There are no people with that name in the database!");
        }
        return people;
    }

    @Override
    public List<Person> getPeopleByLastName(String lastName) {
        List<Person> people = personRepository.findPeopleByLastName(lastName);
        if (people.isEmpty()){
            throw new EntityException("There are no people with that last name in the database!");
        }
        return people;
    }

    @Override
    public List<Person> getPeopleByFatherName(String fatherName) {
        List<Person> people = personRepository.findPeopleByFatherName(fatherName);
        if (people.isEmpty()){
            throw new EntityException("There are no people with that patronymic in the database!");
        }
        return people;
    }

    @Override
    public List<Person> getPeopleByNameAndSurname(String firstName, String lastName) {
        List<Person> people = personRepository.findPeopleByFirstNameAndLastName(firstName,lastName);
        if (people.isEmpty()){
            throw new EntityException("There are no people with that first and last name in the database!");
        }
        return people;
    }

    @Override
    public List<Person> getPeopleByFullName(String firstName, String lastName, String fatherName) {
        List<Person> people = personRepository.findPeopleByFirstNameAndLastNameAndFatherName(firstName, lastName,
                fatherName);
        if (people.isEmpty()){
            throw new EntityException("There are no such people in the database!");
        }
        return people;
    }

    @Override
    public List<Person> getPeopleByGroupName(String groupName) {
        List<Person> people = personRepository.findPeopleByGroupName(groupName);
        if (people.isEmpty()){
            throw new EntityException("There are no people with such group name!");
        }
        return people;
    }

    @Override
    public Map<Integer, Person> getPeopleByMarkValue(int markValue) {
        List<Mark> marks = markRepository.findAllByValue(markValue);
        if (marks.isEmpty()){
            throw new EntityException("There are no people with such mark value = " + markValue + "!");
        }
        Map<Integer, Person> personMap = new HashMap<>();
        for (Mark m:marks){
            personMap.put(m.getStudent().getId(),m.getStudent());
        }
        return personMap;
    }

    @Override
    public Map<Integer, Person> getPeopleBySubject(String subjectName) {
        List<Mark> marks = markRepository.findAllBySubjectName(subjectName);
        if (marks.isEmpty()){
            throw new EntityException("There are no people with subject '" + subjectName + "'!");
        }
        Map<Integer, Person> personMap = new HashMap<>();
        for (Mark m:marks){
            personMap.put(m.getStudent().getId(),m.getStudent());
        }
        return personMap;
    }

    @Override
    public Map<Integer, Person> getPeopleByMarkAndSubject(Integer markValue, String subjectName) {
        List<Mark> marks = markRepository.findAllByValueAndSubjectName(markValue,subjectName);
        if (marks.isEmpty()){
            throw new EntityException("There are no people with such mark =" + markValue + " and subject '"
                    + subjectName + "'!");
        }
        Map<Integer, Person> personMap = new HashMap<>();
        for (Mark m:marks){
            personMap.put(m.getStudent().getId(),m.getStudent());
        }
        return personMap;
    }

    @Override
    public String deletePersonById(Integer id) {
        Optional<Person> personOptional = personRepository.findById(id);
        if (personOptional.isEmpty()){
            throw new EntityException("There is no person in the database with id: " + id);
        }
        Person person = personOptional.get();
        if (person.getType() == 'P'){
            List<Mark> marks = markRepository.findAll();
            for (Mark m:marks){
                if (m.getTeacher().getFirstName().equals(person.getFirstName())
                        && m.getTeacher().getLastName().equals(person.getLastName())){
                    markRepository.deleteById(m.getId());
                }
            }
        }
        personRepository.deleteById(id);
        return "Success delete person with ID " + id;
    }

    public void checkInfo(Person person){
        if (person.getType() != 'S' && person.getType() != 'P'){
            throw new WrongEntityParameterException("Wrong type of person, must be 'S' or 'T'!");
        }
        if (person.getType() == 'P' && person.getGroup() != null){
            throw new WrongEntityParameterException("Teacher can't have a group!");
        }
        if (person.getType() == 'P' && !person.getMarks().isEmpty()){
            throw new WrongEntityParameterException("Teacher can't have a marks!");
        }
        if (person.getType() == 'P'){
            return;
        }
        if (person.getType() == 'S' && person.getGroup() == null){
            throw new WrongEntityParameterException("Student must have a group!");
        }
        /*if (person.getType() == 'S' && (person.getMarks().isEmpty() || person.getMarks() == null)){
            throw new WrongEntityParameterException("Student must have a marks!");
        }*/
        List<Mark> marks = person.getMarks();
        List<String> subjects = marks.stream().map(i -> i.getSubject().getName()).distinct().collect(Collectors.toList());
        System.out.println("Subjects  : " + subjects.size() + "\n Marks : " + marks.size());
        if(marks.size() != subjects.size()) {
            throw new WrongEntityParameterException("Student has marks on same subjects!!!");
        }
        if (person.getType() == 'S') {
            List<Mark> marks1 = person.getMarks();
            for (Mark m : marks1) {
                m.setStudent(person);
            }
        }
    }
}
