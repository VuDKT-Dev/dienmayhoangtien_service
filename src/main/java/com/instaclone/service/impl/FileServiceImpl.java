package com.instaclone.service.impl;

import com.instaclone.domain.MyFile;
import com.instaclone.repository.FileRepository;
import com.instaclone.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileRepository fileRepository;


    @Override
    @Transactional
    public ResponseEntity<?> save(MultipartFile multipartFile, Long uid) throws IOException {
        if (fileRepository.existsByUid(uid, multipartFile.getOriginalFilename()) != null) {
            fileRepository.deleteByUidAndFileName(uid, multipartFile.getOriginalFilename());
        }
        fileRepository.save(convertFile(multipartFile, uid));
        return ResponseEntity.status(200).contentType(MediaType.
                parseMediaType(multipartFile.getContentType())).
                body(multipartFile.getBytes());
    }

    @Override
    public ResponseEntity<?> findByUid(Long uid) {
        MyFile myFile = fileRepository.findByUid(uid);
        if (myFile == null) {
            myFile = fileRepository.findByUid(1L);
        }
        return getResponseEntityFile(myFile);
    }

    public MyFile convertFile(MultipartFile multipartFile, Long uid) {
        MyFile myFile = new MyFile();
        try {
            myFile.setFileName(multipartFile.getOriginalFilename());
            myFile.setFileType(multipartFile.getContentType());
            myFile.setUid(uid);
            myFile.setContent(multipartFile.getBytes());
            return myFile;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<?> getResponseEntityFile(MyFile myFile) {
        return ResponseEntity.status(200).contentType(MediaType.parseMediaType(myFile.getFileType())).body(myFile.getContent());

    }

}
