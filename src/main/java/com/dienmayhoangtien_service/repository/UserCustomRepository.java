package com.dienmayhoangtien_service.repository;

import com.dienmayhoangtien_service.domain.UserCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCustomRepository extends JpaRepository<UserCustom, Long> {
    Optional<UserCustom> findByEmail(String email);

    Optional<UserCustom> findByActiveCode(String activeCode);
    Optional<UserCustom> findByForgotPassword(String forgotPassword);
}
