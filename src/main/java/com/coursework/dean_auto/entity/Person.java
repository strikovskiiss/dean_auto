package com.coursework.dean_auto.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "people")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name")
    @NotNull
    private String firstName;

    @Column(name = "last_name")
    @NotNull
    private String lastName;

    @Column(name = "father_name")
    private String fatherName;

    @Column(name = "type")
    @NotNull
    private Character type;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "group_id")
    private Group group;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private List<Mark> marks;

    public Person(){
        marks = new ArrayList<>();
    }

    public Person(String firstName, String lastName, String fatherName, Character type){
        this.firstName = firstName;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.type = type;
        marks = new ArrayList<>();
    }

    public Person(String firstName, String lastName, String fatherName, Character type, Group group){
        this.firstName = firstName;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.type = type;
        this.group = group;
        marks = new ArrayList<>();
    }

    public void addMark(Mark mark){
        marks.add(mark);
        if (mark.getStudent() == null || mark.getStudent() != this) {
            mark.setStudent(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public Character getType() {
        return type;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public void setType(Character type) {
        this.type = type;
    }

    public List<Mark> getMarks() {
        return marks;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setMarks(List<Mark> marks) {
        this.marks = marks;
    }

    @Override
    public String toString(){
        return "Person { " + "id= " + id
                + ", firstName= " + firstName
                + ", lastName= " + lastName
                + ", fatherName= " + fatherName
                + ", type= " + type + " }";
    }
}
