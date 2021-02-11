package com.test.tbcm.base.service;

import org.springframework.data.domain.Page;

import java.util.Optional;

public interface BaseService<T, K> {

    Page<T> get(int page, int offset) throws Exception;

    Optional<T> findById(long id) throws Exception;

    T save(K dto) throws Exception;

    T saveEntity(T entity) throws Exception;

    void delete(long id) throws Exception;

    void delete(long id, boolean softDelete) throws Exception;

    Page<T> search(String query, int page, int offset) throws Exception;

    Boolean existsById(Long id);

}
