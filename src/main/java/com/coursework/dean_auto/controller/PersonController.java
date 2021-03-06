package com.coursework.dean_auto.controller;

import com.coursework.dean_auto.entity.Group;
import com.coursework.dean_auto.entity.Mark;
import com.coursework.dean_auto.entity.Person;
import com.coursework.dean_auto.entity.Subject;
import com.coursework.dean_auto.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/people_api")
public class PersonController {
    @Autowired
    PersonService personService;

    @PostMapping("/save")
    public Person savePerson(@RequestBody Person person){
        return personService.savePerson(person);
    }

    @PutMapping("/update")
    public Person updatePerson(@RequestBody Person person){
        return personService.updatePerson(person);
    }

    @GetMapping("/id")
    public Person getPersonById(@RequestParam("id") Integer id){
        return personService.getPersonById(id);
    }

    @GetMapping("/all")
    public List<Person> getAllPeople() {
        return personService.getAllPeople();
    }

    @GetMapping("/name")
    public List<Person> getPeopleByFirstName(@RequestParam("name") String name){
        return personService.getPeopleByFirstName(name);
    }

    @GetMapping("/surname")
    public List<Person> getPeopleByLastName(@RequestParam("surname") String surname){
        return personService.getPeopleByLastName(surname);
    }

    @GetMapping("/fathername")
    public List<Person> getPeopleByFatherName(@RequestParam("fatherName") String fatherName) {
        return personService.getPeopleByFatherName(fatherName);
    }

    @GetMapping("/name_surname/")
    public List<Person> getPeopleByNameAndSurname(@RequestParam("name") String name,
                                                  @RequestParam("surname") String surname){
        return personService.getPeopleByNameAndSurname(name,surname);
    }

    @GetMapping("/full_name/{name}_{surname}_{fathername}")
    public List<Person> getPeopleByFullName(@PathVariable String name, @PathVariable String surname,
                                            @PathVariable String fathername){
        return personService.getPeopleByFullName(name,surname,fathername);
    }

    @GetMapping("/group_name")
    public List<Person> getPeopleByGroupName(@RequestParam("groupName") String groupName) {
        return personService.getPeopleByGroupName(groupName);
    }

    @GetMapping("/mark_value/")
    public Map<Integer, Person> getPeopleByMarkValue(@RequestParam("markValue") int markValue) {
        return personService.getPeopleByMarkValue(markValue);
    }

    @GetMapping("/subject/")
    public Map<Integer, Person> getPeopleBySubject(@RequestParam("subjectName") String subjectName) {
        return personService.getPeopleBySubject(subjectName);
    }

    @GetMapping("/mark_subject/{markValue}_{subjectName}")
    public Map<Integer, Person> getPeopleByMarkAndSubject(@PathVariable int markValue, @PathVariable String subjectName)
    {
        return personService.getPeopleByMarkAndSubject(markValue, subjectName);
    }

    @DeleteMapping("/id")
    public String deletePersonById(@RequestParam("id") Integer id) {
        return personService.deletePersonById(id);
    }

    @GetMapping("/example")
    public List<Person> saveAllPeople(){
        Person teacher1 = new Person("Maria", "Petrova", "Alexandrovna", 'P',null);
        Person teacher2 = new Person("Victor", "Alexeev", "Ivanovich",'P',null);
        Group group1 = new Group("a");
        Group group2 = new Group("b");
        Subject subject1 = new Subject("OOP");
        Subject subject2 = new Subject("Math");
        Subject subject3 = new Subject("English");

        Mark mark = new Mark(5);
        Person student1 = new Person("Mark", "Sherbenko", "Sergeevich",  'S');
        student1.setGroup(group1);
        mark.setSubject(subject1);
        mark.setTeacher(teacher1);
        student1.addMark(mark);

        Mark mark2 = new Mark(4);
        mark2.setSubject(subject2);
        mark2.setTeacher(teacher1);
        student1.addMark(mark2);

        Mark mark3 = new Mark(4);
        Person student2 = new Person("Alexey", "Smirnov", "Antonovich", 'S');
        student2.setGroup(group1);
        mark3.setSubject(subject3);
        mark3.setTeacher(teacher2);
        student2.addMark(mark3);

        Mark mark4 = new Mark(4);
        Person student3 = new Person("Ivan", "Ivanov", "Ivanovich", 'S');
        student3.setGroup(group2);
        mark4.setSubject(subject2);
        mark4.setTeacher(teacher1);
        student3.addMark(mark4);

        Person student4 = new Person("Stanislav", "Strong", "Igorevich", 'S');
        Mark mark5 = new Mark(3);
        mark5.setSubject(subject2);
        mark5.setTeacher(teacher1);
        student4.setGroup(group2);
        student4.addMark(mark5);

        Mark mark6 = new Mark(2);
        mark6.setSubject(subject2);
        mark6.setTeacher(teacher1);
        Person student5 = new Person("Kirill", "Smart", "Artemovich", 'S');
        student5.setGroup(group1);
        student5.addMark(mark6);

        personService.savePerson(student1);
        personService.savePerson(student2);
        personService.savePerson(student3);
        personService.savePerson(student4);
        personService.savePerson(student5);

        return getAllPeople();
    }
}
