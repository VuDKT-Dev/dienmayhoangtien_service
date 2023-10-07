package com.instaclone.repository;

import com.instaclone.domain.MyFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<MyFile, Long> {
    List<MyFile> findByUid(Long uid);

    void deleteByUidAndFileName(Long uid, String fileName);

    @Query(value = "SELECT * FROM my_file mf WHERE mf.user_id = ?1 AND mf.file_name = ?2",nativeQuery = true)
    MyFile existsByUid(Long uid, String fileName);
}
