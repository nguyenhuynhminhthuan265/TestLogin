package com.test.tbus.service.impl;

import com.test.tbcm.network.exception.notfound.RoleNotFoundException;
import com.test.tbcm.utils.CPage;
import com.test.tbcm.utils.Constants;
import com.test.tbcm.utils.ModelMapperUtils;
import com.test.tbus.model.dto.RoleDto;
import com.test.tbus.model.entity.Role;
import com.test.tbus.repository.RoleRepository;
import com.test.tbus.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Page<Role> get(int page, int offset) throws Exception {
        CPage newPage = Constants.Page.validate(page, offset);
        Pageable pageable = PageRequest.of(newPage.getPage(), newPage.getOffset());
        return roleRepository.findAll(pageable);
    }

    @Override
    public Optional<Role> findById(long id) throws Exception {
        return roleRepository.findById(id);
    }

    @Override
    public Role save(RoleDto dto) throws Exception {
        if (dto.getId() != null) {
            if (!this.existsById(dto.getId())) {
                throw new RoleNotFoundException();
            }
        }
        return this.saveEntity(ModelMapperUtils.toEntity(dto, Role.class));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Role saveEntity(Role entity) throws Exception {
        return roleRepository.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(long id) throws Exception {
        Role entity = this.findById(id).orElseThrow(RoleNotFoundException::new);
        if (entity.getId() != null) {
            if (!this.existsById(entity.getId())) {
                throw new RoleNotFoundException();
            }
        }
        entity.setDelete(true);
        this.saveEntity(entity);

    }

    @Override
    public void delete(long id, boolean softDelete) throws Exception {

    }

    @Override
    public Page<Role> search(String query, int page, int offset) throws Exception {
        return null;
    }

    @Override
    public Boolean existsById(Long id) {
        return roleRepository.existsById(id);
    }

    @Override
    public Optional<Role> findByName(String name) {
        return roleRepository.findByNameContainingIgnoreCaseAndIsDelete(name, false);
    }
}