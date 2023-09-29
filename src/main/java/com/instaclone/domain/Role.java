package com.instaclone.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROLE_GEN")
    @SequenceGenerator(name = "ROLE_GEN", sequenceName = "ROLE_SEQ", allocationSize = 1)
    private Long id;
    @Column(name = "role_name")
    private String roleName;
    @Column(name = "status")
    private Integer status;
}
