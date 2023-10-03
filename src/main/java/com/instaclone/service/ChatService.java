package com.instaclone.service;

import com.instaclone.domain.Chat;
import org.springframework.http.ResponseEntity;

public interface ChatService {
    ResponseEntity<?> finByCid(long cid);
    ResponseEntity<?> save(Chat chat);
    ResponseEntity<?> delete(long cid);
}
