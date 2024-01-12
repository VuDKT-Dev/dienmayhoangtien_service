package com.dienmayhoangtien_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {
    @GetMapping
    public ResponseEntity<String> demo(){
        String result = "OK. You got it!!!";
        return ResponseEntity.ok(result);
    }
    @GetMapping
    public ResponseEntity<String> demo2(){
        String result = "OK. CI111111111123232111111111111!!!";
        return ResponseEntity.ok(result);
    }
}
