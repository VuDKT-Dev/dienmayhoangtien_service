package com.instaclone.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "GROUP_MEMBER")
public class GroupMember {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GROUP_MEMBER_GEN")
    @SequenceGenerator(name = "GROUP_MEMBER_GEN", sequenceName = "GROUP_MEMBER_SEQ", allocationSize = 1)
    private Long id;


    @Column(name = "group_id")
    private Long groupId;


    @Column(name = "user_id")
    private Long userId;


    @Column(name = "member_type")
    private String memberType;


    @Column(name = "status")
    private String status;

    @ManyToMany(mappedBy = "members")
    Set<Group> groups;

}
