package com.coursework.dean_auto.controller;

import com.coursework.dean_auto.entity.Group;
import com.coursework.dean_auto.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups_api")
public class GroupController {
    @Autowired
    GroupService groupService;

    @GetMapping("/groups/all")
    public List<Group> getAllGroups(){
        return groupService.getAllGroups();
    }

    @GetMapping("/groups/name/{groupName}")
    public List<Group> getAllGroupsByName(@PathVariable String groupName){
        return groupService.getAllGroupsByName(groupName);
    }

/*    @DeleteMapping("/group/id/{id}")
    public String deleteGroupById(@PathVariable Integer id){
        return groupService.deleteGroupById(id);
    }*/
}
