package com.dienmayhoangtien_service.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "USER_DETAIL")
public class UserDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_DETAIL_GEN")
    @SequenceGenerator(name = "USER_DETAIL_GEN", sequenceName = "USER_DETAIL_SEQ", allocationSize = 1)
    private Long id;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "gender")
    private Boolean gender;
    @Column(name = "BIRTHDAY")
    private Date birthday;
    @Column(name = "STATUS")
    @Enumerated(value = EnumType.STRING)
    private EUserStatus status;
    @Column(name = "address")
    private String address;
    @Column(name = "INTEREST")
    private String interests;
    @Column(name = "CREATED_AT")
    private Date createdAt;
    @Column(name = "UPDATED_AT")
    private Date updatedAt;
    @Column(name = "CREATED_BY")
    private Date createdBy;
    @Column(name = "UPDATED_BY")
    private Date updatedBy;
}
