package com.instaclone.repository;

import com.instaclone.domain.Group;
//import com.instaclone.service.GroupService;
import com.instaclone.domain.GroupMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group,Long> {


}
