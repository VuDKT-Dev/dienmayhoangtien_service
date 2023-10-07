package com.instaclone.controller;

import com.instaclone.config.FileConfiguration;
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

    @Autowired
    private FileConfiguration fileConfiguration;

    @GetMapping
    public ResponseEntity<?> getFile(@RequestParam Long id){
        fileConfiguration.multipartConfigElement();
        return fileService.findByUid(id);
    }

    @PostMapping
    public ResponseEntity<?> saveFile(@RequestParam Long id, @RequestParam MultipartFile file) throws IOException {
        fileConfiguration.multipartConfigElement();
        return fileService.save(file, id);
    }

}
