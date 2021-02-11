package com.test.tbus.controller;

import com.test.tbcm.config.Route;
import com.test.tbcm.network.Message;
import com.test.tbcm.network.MessagesUtils;
import com.test.tbcm.network.ResponseUtil;
import com.test.tbcm.network.exception.notfound.UserNotFoundException;
import com.test.tbcm.security.JwtTokenUtil;
import com.test.tbcm.utils.DateUtil;
import com.test.tbcm.utils.LoggerHelperUtils;
import com.test.tbcm.utils.ModelMapperUtils;
import com.test.tbus.model.dto.FullUserDto;
import com.test.tbus.model.dto.LoginUserDto;
import com.test.tbus.model.entity.User;
import com.test.tbus.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping(Route.V1.prefixApi)
public class LoginController {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    ResponseUtil responseUtil;

    @Autowired
    AuthenticationManager authenticationManager;

    // API: S1.0
    @PostMapping(Route.V1.LOGIN_USER)
    public ResponseEntity login(@RequestBody LoginUserDto userDto) throws Exception {
        LoggerHelperUtils.logger("TEST LOGIN", userDto.toString());
        User userInService = userService.findByEmail(userDto.getEmail()).orElseThrow(UserNotFoundException::new);

        // Tạo đối tượng chứa email, password
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(userDto.getEmail(),
                userDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);


        // Generate Token
        String accessToken = jwtTokenUtil.generateToken(userDto.getEmail());
        Date tokenExpired = jwtTokenUtil.getExpirationDateFromToken(accessToken);
        // Update User Information
        userInService.setAccessToken(accessToken);
        userInService.setExpiredToken(DateUtil.toZoneDateTime(tokenExpired));
        userService.saveEntity(userInService);
        // End

        Message message = new Message();
        message.setCode(MessagesUtils.MESSAGE_LOGIN_SUCCESS);
        return new ResponseEntity(ModelMapperUtils.toDto(userInService, FullUserDto.class), HttpStatus.OK);


    }
}
