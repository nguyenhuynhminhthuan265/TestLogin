package com.test.tbus.service;

import com.test.tbcm.base.service.BaseService;
import com.test.tbus.model.dto.RoleDto;
import com.test.tbus.model.entity.Role;

import java.util.Optional;

public interface RoleService extends BaseService<Role, RoleDto> {
    Optional<Role> findByName(String name);
}
