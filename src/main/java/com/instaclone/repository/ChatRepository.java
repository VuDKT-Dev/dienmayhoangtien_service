package com.instaclone.repository;

import com.instaclone.domain.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    @Query(value = "select * from chat_data as c where c.cid=?1",nativeQuery = true)
    List<Chat> findByCidIgnoreCase(long cid);
}
