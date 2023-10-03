package com.instaclone.service.impl;

import com.instaclone.common.respone.BaseResponse;
import com.instaclone.domain.Chat;
import com.instaclone.repository.ChatRepository;
import com.instaclone.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl extends BaseResponse implements ChatService {
    @Autowired
    private ChatRepository repository;


    @Override
    public ResponseEntity<?> finByCid(long cid) {
        return getResponseEntity(repository.findByCidIgnoreCase(cid));
    }

    @Override
    public ResponseEntity<?> save(Chat chat) {
        Chat chat1= repository.save(chat);
        return getResponseEntity(chat1);
    }

    @Override
    public ResponseEntity<?> delete(long cid) {
        repository.deleteById(cid);
        return getResponseEntity("Delete ok");
    }
}
