package com.test.fillData;

import com.test.tbcm.utils.Constants;
import com.test.tbms.model.entity.MessageEntity;
import com.test.tbms.repository.MessageEntityRepository;
import com.test.tbus.model.dto.FullUserDto;
import com.test.tbus.model.dto.RoleDto;

import com.test.tbus.service.RoleService;
import com.test.tbus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class FillDataExample {
    @Autowired
    private RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    MessageEntityRepository messageEntityRepository;

    //    @PostConstruct
    public void fillData() throws Exception {
        fillDataRoleExample();

        fillDataUserExample();


    }


    public void fillDataRoleExample() throws Exception {
        roleService.save(new RoleDto("ROLE_ADMIN"));
        roleService.save(new RoleDto("ROLE_MANAGER"));
        roleService.save(new RoleDto("ROLE_USER"));


    }


    public void fillDataUserExample() throws Exception {
        userService.save(new FullUserDto("admin1@gmail.com", "admin1", "123456", 1l));
        userService.save(new FullUserDto("manager1@gmail.com", "manager1", "123456", 2l));
        userService.save(new FullUserDto("admin2@gmail.com", "admin2", "123456", 1l));
        userService.save(new FullUserDto("manager2@gmail.com", "manager2", "123456", 2l));
        userService.save(new FullUserDto("admin3@gmail.com", "admin3", "123456", 1l));
        userService.save(new FullUserDto("manager3@gmail.com", "manager3", "123456", 2l));


        userService.save(new FullUserDto("user1@gmail.com", "user1", "123456", 3l));
        userService.save(new FullUserDto("user2@gmail.com", "user2", "123456", 3l));
        userService.save(new FullUserDto("user3@gmail.com", "user3", "123456", 3l));


    }

    //    @PostConstruct
    private void fillDataMessageEntity() throws Exception {
        List<MessageEntity> entities = new ArrayList<>();
        entities.add(new MessageEntity(Constants.Messages.MESSAGE_NOT_FOUND, "Không tìm được tin nhắn", "Message not found"));

        entities.add(new MessageEntity(Constants.Messages.ROLE_NOT_FOUND, "Không tìm được quyền", "Role not found"));

        entities.add(new MessageEntity(Constants.Messages.USER_NOT_FOUND, "Không tìm được tài khoản", "User not found"));

        entities.add(new MessageEntity(Constants.Messages.AUTHENTICATION_EXCEPTION, "Sai tên đăng nhập hoặc mật khẩu", "Email or Password Wrong"));

        entities.add(new MessageEntity(Constants.Messages.ERROR_NOT_FOUND, "Không tìm được thông báo lỗi tương ứng", "Error not found"));

        messageEntityRepository.saveAll(entities);
    }

}
