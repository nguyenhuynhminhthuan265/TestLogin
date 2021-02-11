package com.test.tbcm.security;

import com.google.gson.Gson;
import com.test.tbcm.network.Message;
import com.test.tbcm.network.Meta;
import com.test.tbcm.network.Response;
import com.test.tbcm.network.ResponseUtil;
import com.test.tbcm.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    @Autowired
    HttpServletRequest request;

    @Autowired
    ResponseUtil responseUtil;

    private static final long serialVersionUID = -7858869558953243875L;
    private String jsonDefault = "{ \"status\": false, \"code\": 401 }";

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        System.out.println("========================>>>>>>>>>>>>>>>>>>>> commence");
        try {
            Message message = new Message();
            message.setCode(Constants.Messages.ERROR_NOT_FOUND);
            responseUtil.getMessages(request, message);
            // END
            Response responseBody = new Response<String, Message, Meta>();
            responseBody.setStatus(false);
            responseBody.setCode(HttpStatus.UNAUTHORIZED.value());
            responseBody.setMessages(message);
            responseBody.setData(null);
            response.getWriter().write(new Gson().toJson(responseBody));
        } catch (Exception e) {
            response.getWriter().write(jsonDefault);
        }
    }

}