package com.instaclone.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "group1")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GROUP_GEN")
    @SequenceGenerator(name = "GROUP_GEN", sequenceName = "GROUP_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "description")
    private String description;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "background")
    private String background;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "phone", length = 15)
    private String phone;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "createdate")
    private Date createDate;

    @Column(name = "favorites", length = 1023)
    private String favorites;

    @Column(name = "created_at")
    private Date createdAt;


    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToMany
    @JoinTable(
            name = "group_member_mapping",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "groupId")
    )
    Set<GroupMember> members;
}



