package com.test.tbms.repository;

import com.test.tbms.model.entity.MessageEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageEntityRepository extends JpaRepository<MessageEntity, Long> {

    Page<MessageEntity> findAllByIsDelete(Boolean isDelete, Pageable pageable);

    Optional<MessageEntity> findByIdAndIsDelete(long id, Boolean isDelete);

    Page<MessageEntity> findMessagesByTextVIContainingIgnoreCaseOrTextENContainingIgnoreCaseAndIsDelete(Pageable pageable, String textVI, String textEN, Boolean isDelete);
}
