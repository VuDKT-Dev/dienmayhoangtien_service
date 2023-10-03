package com.instaclone.common.respone;

import org.springframework.http.ResponseEntity;

public class BaseResponse {
     protected ResponseEntity<?> getResponseEntity(Object data){
         return ResponseEntity.ok(getResponse(data));
     }
//    protected ResponseEntity<?> getResponseEntityFile(File file12) {
//        return ResponseEntity.status(200).contentType(MediaType
//                        .parseMediaType(file12.getCtype()))
//                .body(file12.getContent());
//
//    }
//    protected File convertFile(MultipartFile file, int uid){
//        File file12 = new File();
//        try {
//            file12.setFname(file.getOriginalFilename());
//            file12.setCtype(file.getContentType());
//            file12.setUid(uid);
//            file12.setContent(file.getBytes());
//            return file12;
//        }catch (Exception e){
//            throw new RuntimeException(e);
//        }
//    }

    private MyResponse getResponse(Object data){
         MyResponse myResponse = new MyResponse();
         myResponse.setData(data);
        myResponse.setStatus("200");
        myResponse.setMessage("success");
        return myResponse;
    }
}
