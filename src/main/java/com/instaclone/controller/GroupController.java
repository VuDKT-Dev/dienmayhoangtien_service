package com.instaclone.controller;

import com.instaclone.domain.Group;
import com.instaclone.domain.GroupMember;
import com.instaclone.domain.UserCustom;
import com.instaclone.repository.UserCustomRepository;
import com.instaclone.service.impl.GroupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/groups")
public class GroupController {
    @Autowired
    private GroupServiceImpl groupService;
    @Autowired
    private UserCustomRepository customRepository;

    @GetMapping
    public ResponseEntity<List<Group>> getAllGroup() {
        List<Group> groups = groupService.findAll();
        return ResponseEntity.ok(groups);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Group> getGroupById(@PathVariable Long id) {
        Optional<Group> group = groupService.findById(id);
        if (group.isPresent()) {
            Group group1 = group.get();
            return ResponseEntity.ok(group1);

        } else {
            return ResponseEntity.notFound().build();
        }


    }

    @PostMapping
    public ResponseEntity<Group> createGroup(@RequestBody Group group) {
        Group createGroup = groupService.create(group);
        return ResponseEntity.ok(createGroup);


    }

    @PutMapping("/{id}")
    public ResponseEntity<Group> updateGroup(@PathVariable Long id, @RequestBody Group group) {
        Group updatedGroup = groupService.update(id, group);
        return ResponseEntity.ok(updatedGroup);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGroup(@PathVariable Long id) {
        boolean deleted = groupService.deleteById(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Không thể xóa nhóm với ID " + id);
        }

    }

}
