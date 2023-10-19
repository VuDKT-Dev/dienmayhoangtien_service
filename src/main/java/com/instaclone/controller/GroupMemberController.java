package com.instaclone.controller;

import com.instaclone.domain.Group;
import com.instaclone.domain.GroupMember;
import com.instaclone.service.GroupMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/groupmembers")
public class GroupMemberController {
    @Autowired
    private GroupMemberService groupMemberService;

    // Định nghĩa các phương thức xử lý yêu cầu tương tác với GroupMember

    @GetMapping
    public ResponseEntity<List<GroupMember>> getAllGroupMember() {
        List<GroupMember> groupMembers = groupMemberService.findAll();
        return ResponseEntity.ok(groupMembers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupMember> getGroupMemberById(@PathVariable Long id) {
        Optional<GroupMember> groupMembers = groupMemberService.findById(id);
        if (groupMembers.isPresent()) {
            GroupMember groupMember = groupMembers.get();
            return ResponseEntity.ok(groupMember);
        } else {
            return ResponseEntity.notFound().build();

        }

    }

    @PostMapping("/add")
    public ResponseEntity<?> addGroupMember(@RequestBody GroupMember member) {
        boolean isMemberExists = groupMemberService.isMemberExists(member.getGroupId(), member.getUserId());
        if (isMemberExists) {
            return ResponseEntity.badRequest().body("Member already in the group");
        } else {
            GroupMember addMember = groupMemberService.create(member);
            if (addMember != null) {
                return ResponseEntity.ok(addMember);

            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }

        }
    }

    @PutMapping("/{userId}/update-member")
    public ResponseEntity<GroupMember> updateMember(@PathVariable Long userId, @RequestBody GroupMember updatedMember) {
        Optional<GroupMember> optionalGroupMember = groupMemberService.findByUserId(userId);

        if (optionalGroupMember.isPresent()) {
            GroupMember result = groupMemberService.updateByUserId(userId, updatedMember);
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteMember(@PathVariable Long userId) {
        boolean deleted = groupMemberService.deleteByUserId(userId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Không thể xóa thành viên với id" + userId);
        }
    }
}

