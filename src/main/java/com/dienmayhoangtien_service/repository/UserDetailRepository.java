package com.dienmayhoangtien_service.repository;

import com.dienmayhoangtien_service.domain.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {
}
