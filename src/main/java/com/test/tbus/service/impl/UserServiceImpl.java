package com.test.tbus.service.impl;

import com.test.tbcm.network.exception.notfound.UserNotFoundException;
import com.test.tbcm.utils.CPage;
import com.test.tbcm.utils.Constants;
import com.test.tbcm.utils.ModelMapperUtils;
import com.test.tbus.model.dto.FullUserDto;
import com.test.tbus.model.entity.User;
import com.test.tbus.repository.UserRepository;
import com.test.tbus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Page<User> get(int page, int offset) throws Exception {
        CPage newPage = Constants.Page.validate(page, offset);
        Pageable pageable = PageRequest.of(newPage.getPage(), newPage.getOffset());
        return userRepository.findAllByIsDelete(false, pageable);
    }

    @Override
    public Optional<User> findById(long id) throws Exception {
        return userRepository.findById(id);
    }

    @Override
    public User save(FullUserDto dto) throws Exception {
        if (dto.getId() != null) {
            if (!this.existsById(dto.getId())) {
                throw new UserNotFoundException();
            }
        }

        // mã hóa mật khẩu
        String hashed = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt(12));
        System.out.println("password encoding: " + hashed);
        dto.setPassword(hashed);
        return this.saveEntity(ModelMapperUtils.toEntity(dto, User.class));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User saveEntity(User entity) throws Exception {
        return userRepository.save(entity);
    }

    @Override
    public void delete(long id) throws Exception {

    }

    @Override
    public void delete(long id, boolean softDelete) throws Exception {

    }

    @Override
    public Page<User> search(String query, int page, int offset) throws Exception {
        return null;
    }

    @Override
    public Boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmailAndIsDelete(email, false);
    }

    @Override
    public Optional<User> findByToken(String token) {
        return Optional.empty();
    }

    @Override
    public User update(FullUserDto object) {
        return null;
    }
}
