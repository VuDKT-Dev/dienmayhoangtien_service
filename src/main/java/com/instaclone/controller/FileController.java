package com.instaclone.controller;

import com.instaclone.domain.MyFile;
import com.instaclone.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/my-file")
public class FileController {
    @Autowired
    private FileService fileService;

    @GetMapping
    public ResponseEntity<?> getFile(@RequestParam Long id){
        return fileService.findByUid(id);
    }

    @PostMapping
    public ResponseEntity<?> saveFile(@RequestParam Long id, @RequestParam MultipartFile file) throws IOException {
        return fileService.save(file, id);
    }

}
