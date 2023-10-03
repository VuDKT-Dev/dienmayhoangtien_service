package com.instaclone.controller;

import com.instaclone.chatWebSoket.ChatMessage;
import com.instaclone.domain.Chat;
import com.instaclone.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/chat")
public class ChatController {
    @Autowired
    private ChatService service;
    @GetMapping("/find")
    private ResponseEntity<?> find(@RequestParam int cid){
        return service.finByCid(cid);
    }
    @PostMapping("/save")
    private ResponseEntity<?> save(@RequestBody Chat chat){
        return service.save(chat);
    }
    @GetMapping("/delete")
    private ResponseEntity<?> delete(@RequestParam long cid){
        return service.delete(cid);
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage){
        return chatMessage;
    }


    // add username in web soket session
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor){
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
}
