package com.coursework.dean_auto.service;

import com.coursework.dean_auto.entity.Group;

import java.util.List;

public interface GroupService {
    List<Group> getAllGroups();
    List<Group> getAllGroupsByName(String groupName);
/*    String deleteGroupById(Integer id);*/
}
