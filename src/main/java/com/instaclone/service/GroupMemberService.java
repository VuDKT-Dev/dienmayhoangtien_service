package com.instaclone.service;

import com.instaclone.domain.GroupMember;

import java.util.List;
import java.util.Optional;

public interface GroupMemberService {
    Optional<GroupMember> findById(Long id);
    List<GroupMember> findAll();
    GroupMember create(GroupMember groupMember);
    GroupMember update(Long id, GroupMember groupMember);
    boolean deleteById(Long id);
    boolean isMemberExists(Long groupId, Long userId );
    Optional<GroupMember> findByUserId(Long userId);
    GroupMember updateByUserId(Long userId, GroupMember member);
    boolean deleteByUserId(Long userId);



}
