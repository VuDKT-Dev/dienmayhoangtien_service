package com.instaclone.service;

import com.instaclone.domain.Group;


import java.util.List;
import java.util.Optional;

public interface GroupService {
    Optional<Group> findById(Long id);
    List<Group> findAll();
    Group create(Group group);
    Group update(Long id, Group group);
    boolean deleteById(Long id);

}




