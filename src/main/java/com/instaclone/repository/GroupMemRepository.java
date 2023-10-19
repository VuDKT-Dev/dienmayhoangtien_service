package com.instaclone.repository;

import com.instaclone.domain.Group;
import com.instaclone.domain.GroupMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GroupMemRepository extends JpaRepository<GroupMember, Long> {
    Optional<GroupMember> findByGroupIdAndUserId(@Param("groupId")Long groupId, @Param("userId") Long userId);
    Optional<GroupMember> findByUserId(Long userId);
    GroupMember deleteByUserId(Long userId);

}
