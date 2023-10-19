package com.instaclone.service.impl;

import com.instaclone.domain.Group;
import com.instaclone.repository.GroupRepository;
import com.instaclone.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupRepository groupRepository;


    @Override
    public Optional<Group> findById(Long id) {
        return groupRepository.findById(id);
    }

    @Override
    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    @Override
    public Group create(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public Group update(Long id, Group group) {
        if (groupRepository.existsById(id)) {
            group.setId(id);
            return groupRepository.save(group);
        }else {
            return null;
        }
    }


    @Override
    public boolean deleteById(Long id) {
        groupRepository.deleteById(id);
        return false;
    }

}