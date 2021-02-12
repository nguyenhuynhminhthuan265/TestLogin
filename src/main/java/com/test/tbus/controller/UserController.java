package com.test.tbus.controller;

import com.test.tbcm.config.Route;
import com.test.tbcm.network.*;
import com.test.tbcm.network.exception.notfound.UserNotFoundException;
import com.test.tbcm.utils.DateUtil;
import com.test.tbcm.utils.LoggerHelperUtils;
import com.test.tbcm.utils.ModelMapperUtils;
import com.test.tbus.model.dto.FullUserDto;
import com.test.tbus.model.dto.FullUserDto2;
import com.test.tbus.model.dto.UserTokenDto;
import com.test.tbus.model.entity.User;
import com.test.tbus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(Route.V1.prefixApi)
public class UserController {
    @Autowired
    UserService userServiceImpl;

    @Autowired
    ResponseUtil responseUtil;

    @Autowired
    HttpServletRequest request;

    //API: S2.0
    @GetMapping(Route.V1.GET_USER)
    public CustomResponse<Response<List<FullUserDto2>, Message, Meta>> getListUser(@RequestParam(value = "page") int page,
                                                                                   @RequestParam(value = "offset") int offset) throws Exception {
        Message message = new Message();
        Page<User> pEntities = userServiceImpl.get(page, offset);
        Meta meta = new Meta(page, pEntities.getTotalPages(), pEntities.getTotalElements());
        message.setCode(MessagesUtils.MESSAGE_GET_SUCCESS);
        return responseUtil.buildSuccessResponse(request, ModelMapperUtils.toListDto(pEntities.getContent(), FullUserDto2.class), message, meta);

    }

    // API: S2.1
    @GetMapping(Route.V1.FIND_USER_BY_ID)
    public CustomResponse<Response<FullUserDto2, Message, Meta>> findById(@PathVariable("id") long id) throws Exception {
        Message message = new Message();
        User entity = userServiceImpl.findById(id).orElseThrow(UserNotFoundException::new);
        FullUserDto2 dto = ModelMapperUtils.toDto(entity, FullUserDto2.class);
        dto.setTimeExpiredToken(DateUtil.convertZonedDateTimeToString(dto.getExpiredToken()));
        message.setCode(MessagesUtils.MESSAGE_GET_SUCCESS);
        return responseUtil.buildSuccessResponse(request, dto, message);
    }

    // API: S2.2
    @PostMapping(Route.V1.CREATE_USER)
    public CustomResponse<Response<FullUserDto2, Message, Meta>> create(@RequestBody FullUserDto userDto) throws Exception {
        userDto.setId(null);
        LoggerHelperUtils.logger(getClass().getSimpleName(), "user dto: " + userDto.toString());
        Message message = new Message();
        User entity = userServiceImpl.save(userDto);
        message.setCode(MessagesUtils.MESSAGE_CREATE_SUCCESS);
        return responseUtil.buildSuccessResponse(request, ModelMapperUtils.toDto(entity, FullUserDto2.class), message);

    }

    // API: S2.3
    @PostMapping(Route.V1.FIND_USER_BY_TOKEN)
    public CustomResponse<Response<FullUserDto2, Message, Meta>> findByToken(@RequestBody UserTokenDto userTokenDto) throws Exception {
        Message message = new Message();
        User entity = userServiceImpl.findByToken(userTokenDto.getToken()).orElseThrow(UserNotFoundException::new);
        FullUserDto2 dto = ModelMapperUtils.toDto(entity, FullUserDto2.class);
        message.setCode(MessagesUtils.MESSAGE_GET_SUCCESS);
        return responseUtil.buildSuccessResponse(request, dto, message);
    }
}
