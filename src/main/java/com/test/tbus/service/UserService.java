package com.test.tbus.service;

import com.test.tbcm.base.service.BaseService;
import com.test.tbus.model.dto.FullUserDto;
import com.test.tbus.model.entity.User;

import java.util.Optional;


public interface UserService extends BaseService<User, FullUserDto> {

    Optional<User> findByEmail(String email);

    Optional<User> findByToken(String token);

    User update(FullUserDto object);


}
