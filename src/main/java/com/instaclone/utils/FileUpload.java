package com.instaclone.utils;

import com.instaclone.domain.MyFile;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileUpload {
    private final String UPLOAD_FOLDER = "D:\\FileUpload";

    public boolean uploadImage(MultipartFile multipartFile) {
        boolean isUpload = false;
        try {
            Files.copy(multipartFile.getInputStream(), Paths.get(UPLOAD_FOLDER + File.separator, multipartFile.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            isUpload = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isUpload;
    }

    public boolean checkExisted(MultipartFile multipartFile) {
        boolean isExist = false;
        try {
            File file = new File(UPLOAD_FOLDER + "\\" + multipartFile.getOriginalFilename());
            isExist = file.exists();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isExist;
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

    public ResponseEntity<?> getResponseEntityFile(List<MyFile> myFileList) {
//        List<ResponseEntity<?>> responseEntity = new ArrayList<>();
//        List<byte[]> imageBytesList = new ArrayList<>();
//        byte[] imageBytes = new byte[0];
//        for (MyFile list : myFileList) {
//            try {
//                // Đọc hình ảnh từ tệp hoặc nguồn dữ liệu của bạn và thêm vào danh sách
//                imageBytes = Files.readAllBytes(new File(UPLOAD_FOLDER + "\\" + list.getFileName()).toPath());
//                imageBytesList.add(imageBytes);
//                ResponseEntity<?> response = ResponseEntity.status(200).contentType(MediaType.parseMediaType(list.getFileType())).body(list.getContent());
//                responseEntity.add(response);
//            } catch (IOException e) {
//                e.printStackTrace();
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//            }
//        }
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(myFileList);
//        return ResponseEntity.ok(responseEntity);
    }
}
