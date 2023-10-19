package com.instaclone.service.impl;

import com.instaclone.domain.GroupMember;
import com.instaclone.repository.GroupMemRepository;
import com.instaclone.service.GroupMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class GroupMemberServiceImpl implements GroupMemberService {
    @Autowired
    GroupMemRepository repository;

    @Override
    public Optional<GroupMember> findById(Long id) {
        return repository.findById(id);
    }


    @Override
    public List<GroupMember> findAll() {
        return repository.findAll();
    }

    @Override
    public GroupMember create(GroupMember groupMember) {
        return repository.save(groupMember);
    }

    @Override
    public GroupMember update(Long id, GroupMember groupMember) {
        if (repository.existsById(id)) {
            groupMember.setId(id);
            return repository.save(groupMember);
        } else {
            return null;
        }
    }


    @Override
    public boolean deleteById(Long id) {
        repository.deleteById(id);
       return false;
    }

    @Override
    public boolean isMemberExists(Long groupId, Long userId) {
       Optional<GroupMember> existingMember = repository.findByGroupIdAndUserId(groupId, userId);
       return existingMember.isPresent();
    }

    @Override
    public Optional<GroupMember> findByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public GroupMember updateByUserId(Long userId, GroupMember groupMember) {
        // Tìm GroupMember theo userId
        Optional<GroupMember> optionalGroupMember = repository.findByUserId(userId);

        if (optionalGroupMember.isPresent()) {
            GroupMember existingMember = optionalGroupMember.get();

            // Cập nhật thông tin của GroupMember
            existingMember.setMemberType(groupMember.getMemberType());
            existingMember.setStatus(groupMember.getStatus());

            // Lưu lại thông tin cập nhật
            return repository.save(existingMember);
        } else {
            // Không tìm thấy GroupMember với userId đã chỉ định
            return null;
        }
    }

    @Override
    public boolean deleteByUserId(Long userId) {
        repository.deleteByUserId(userId);
        return false;
    }

}


