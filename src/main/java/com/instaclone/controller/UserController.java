package com.instaclone.controller;

import com.instaclone.domain.UserCustom;
import com.instaclone.repository.UserCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserCustomRepository userCustomRepository;
    @GetMapping
    public ResponseEntity<List<UserCustom>> getAllUser(){
        List<UserCustom> userList = userCustomRepository.findAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
}
