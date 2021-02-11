package com.test.tbus.repository;

import com.test.tbus.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findAllByIsDelete(Boolean isDelete, Pageable pageable);

    Optional<User> findByEmailAndIsDelete(String email, Boolean isDelete);
}
