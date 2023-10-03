package com.instaclone.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CHAT_DATA")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEMP_ID")
    private long tempId;
    @Column(name = "CID")
    private long cid;
    @Column(name = "UID1")
    private long uid1;
    @Column(name = "UID2")
    private long uid2;
    @Column(name = "CONTENT")
    private String content;
    @Column(name = "FILE_ID")
    private long fid;

}
