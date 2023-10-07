package com.instaclone.service.impl;

import com.instaclone.domain.MyFile;
import com.instaclone.repository.FileRepository;
import com.instaclone.service.FileService;
import com.instaclone.utils.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private FileUpload fileUpload;

    @Override
    @Transactional
    public ResponseEntity<?> save(MultipartFile multipartFile, Long uid) throws IOException {
        if (fileRepository.existsByUid(uid, multipartFile.getOriginalFilename()) != null &&
        fileUpload.checkExisted(multipartFile) == true) {
            fileRepository.deleteByUidAndFileName(uid, multipartFile.getOriginalFilename());
        }
        fileUpload.uploadImage(multipartFile);
        fileRepository.save(fileUpload.convertFile(multipartFile, uid));
        return ResponseEntity.status(200).contentType(MediaType.
                parseMediaType(multipartFile.getContentType())).
                body(multipartFile.getBytes());
    }

    @Override
    public ResponseEntity<?> findByUid(Long uid) {
        List<MyFile> myFileList = fileRepository.findByUid(uid);
        if (myFileList == null) {
            myFileList = fileRepository.findByUid(1L);
        }
        return fileUpload.getResponseEntityFile(myFileList);
    }

}
