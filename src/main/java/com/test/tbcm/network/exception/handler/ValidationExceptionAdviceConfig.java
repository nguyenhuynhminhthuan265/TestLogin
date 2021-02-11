package com.test.tbcm.network.exception.handler;

import com.test.tbcm.network.Message;
import com.test.tbcm.network.MessagesUtils;
import com.test.tbcm.network.ResponseUtil;
import com.test.tbcm.network.exception.notfound.NotfoundException;
import com.test.tbcm.network.exception.notfound.UserNotFoundException;
import com.test.tbcm.utils.Constants;
import com.test.tbcm.utils.LoggerHelperUtils;
import com.test.tbms.model.dto.MessageEntityDto;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@RestController
public class ValidationExceptionAdviceConfig {

    @Autowired
    ResponseUtil responseUtil;

    private Message handlerError(Exception ex, Message message) {
        if (ex != null) {
            message.setIntervalMessage(ex.getMessage());
        }
        if (message.getIntervalMessage() == null || message.getIntervalMessage().length() < 2) {
            message.setIntervalMessage(ex.getClass().getSimpleName());
        }
        return message;
    }

    @ExceptionHandler({AuthenticationException.class, BadCredentialsException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    Object handelAuthenticationException(AuthenticationException ex, HttpServletRequest request) {
        Message message = new Message();
        handlerError(ex, message);
        message.setCode(Constants.Messages.AUTHENTICATION_EXCEPTION);
        return responseUtil.buildErrorResponse(request, null, message);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    Object exception(Exception ex, HttpServletRequest request) {
        Message message = new Message();
        handlerError(ex, message);
        message.setCode(Constants.Messages.ERROR_NOT_FOUND);
        return responseUtil.buildErrorResponse(request, null, message);
    }

    @ExceptionHandler(NotfoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    Object handleNotFoundException(NotfoundException ex, HttpServletRequest request) {
        LoggerHelperUtils.logger("Simple Name", ex.getClass().getSimpleName());
        LoggerHelperUtils.logger("Name", ex.getClass().getSimpleName());
        Message message = new Message();
        if (ex != null) {
            message.setIntervalMessage(ex.getMessage());
        }
        long codeError;
        switch (ex.getClass().getSimpleName()) {


            case "MessageEntityNotFoundException":
                codeError = Constants.Messages.MESSAGE_NOT_FOUND;
                break;


            case "UserNotFoundException":
                codeError = Constants.Messages.USER_NOT_FOUND;
                break;

            default:
                codeError = Constants.Messages.ERROR_NOT_FOUND;
                ex.printStackTrace();
                break;
        }

        LoggerHelperUtils.logger("ERROR CODE", codeError);
        message.setCode(codeError);
        return responseUtil.buildErrorResponse(request, null, message);

    }


//    @ExceptionHandler(NullPointerException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ResponseBody
//    Object handleNullPointerException(NullPointerException ex, HttpServletRequest request) {
//        Message message = new Message();
//
//        message.setCode(MessagesUtils.MESSAGE_CREATE_SUCCESS);
//        return responseUtil.buildSuccessResponse(request, ModelMapperUtils.toListDto(entityUploads, UploadDetailDto.class), message);
//    }

}
