package com.instaclone.service;

import com.instaclone.domain.MyFile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    ResponseEntity<?> save(MultipartFile multipartFile , Long uid) throws IOException;

    ResponseEntity<?> findByUid(Long uid);
}
