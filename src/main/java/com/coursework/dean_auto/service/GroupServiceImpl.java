package com.coursework.dean_auto.service;

import com.coursework.dean_auto.entity.Group;
import com.coursework.dean_auto.entity.Person;
import com.coursework.dean_auto.exception.EntityException;
import com.coursework.dean_auto.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService{
    @Autowired
    GroupRepository groupsRepository;

    @Override
    public List<Group> getAllGroups(){
        List<Group> groups = groupsRepository.findAll();
        if (groups.isEmpty()){
            throw new EntityException("There are no groups in this database!");
        }
        return groups;
    }

    @Override
    public List<Group> getAllGroupsByName(String groupName){
        List<Group> groups = groupsRepository.findAllByName(groupName);
        if (groups.isEmpty()){
            throw new EntityException("There are no groups with that name in this database!");
        }
        return groups;
    }

/*    @Override
    public String deleteGroupById(Integer id) {
        Optional<Group> groupOptional = groupsRepository.findById(id);
        if (groupOptional.isEmpty()){
            throw new EntityException("There is no group in the database with id: " + id);
        }
        Group group = groupOptional.get();

        return "The group with id= " + id + " has been deleted!";
    }*/
}
