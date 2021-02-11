package com.test.tbcm.network;

import com.test.tbcm.network.exception.notfound.MessageEntityNotFoundException;
import com.test.tbcm.utils.Constants;
import com.test.tbms.model.entity.MessageEntity;
import com.test.tbms.repository.MessageEntityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class ResponseUtil {

    // TODO: Import autowire service de get message

    private static final String DEFAULT_LANGUAGE = "jp";
    private static final String HEADER_ACCEPT_LANGUAGE = "Accept-Language";
    private static final String LANGUAGE_VI = "vi";
    private static final String LANGUAGE_END = "eng";
    private Logger log = LoggerFactory.getLogger(ResponseUtil.class);
    MessageEntityRepository messageEntityRepository;

    public ResponseUtil(MessageEntityRepository messageEntityRepository) {
        this.messageEntityRepository = messageEntityRepository;
    }

    private <T, S, K> CustomResponse<Response<T, S, K>> buildApiResponse(Response<T, S, K> body) {
        return new CustomResponse(body, HttpStatus.OK);
    }

    // ----------------------------------------------------------------------------------------
    // Custom Response Success
    // ----------------------------------------------------------------------------------------
    private <T, S, K> CustomResponse<Response<T, S, K>> buildSuccessBaseResponse(T body, S message, K meta) {
        Response responseBody = new Response<T, S, K>();
        responseBody.status = true;
        responseBody.code = HttpStatus.OK.value();
        responseBody.data = body;
        responseBody.messages = message;
        responseBody.meta = meta;
        return buildApiResponse(responseBody);
    }

    public <T> CustomResponse<Response<T, Message, Meta>> buildSuccessResponse(HttpServletRequest request, T body, Message message) {
        Message tmpMessage = getMessages(request, message);
        return buildSuccessBaseResponse(body, tmpMessage, null);
    }


    public <T> CustomResponse<Response<T, Message, Meta>> buildSuccessResponse(HttpServletRequest request, T body, Message message, Meta meta) {
        Message tmpMessage = getMessages(request, message);
        validateMeta(meta);
        return buildSuccessBaseResponse(body, tmpMessage, meta);
    }

    public <T> CustomResponse<Response<T, List<Message>, Meta>> buildListSuccessResponse(HttpServletRequest request, T body, List<Message> message, Meta meta) {
        List<Message> messageList = getMessages(request, message);
        validateMeta(meta);
        return buildSuccessBaseResponse(body, messageList, meta);
    }

    private void validateMeta(Meta meta) {
        Math.min(meta.currentPage, Constants.Page.DEFAULT_PAGE);
        Math.min(meta.totalPage, Constants.Page.DEFAULT_TOTAL_PAGES);
        Math.min(meta.totalElements, Constants.Page.DEFAULT_TOTAL_ELEMENTS);
    }

    // ----------------------------------------------------------------------------------------
    // Custom Response Error
    // ----------------------------------------------------------------------------------------
    private <T, S, K> CustomResponse<Response<T, S, K>> buildErrorBaseResponse(int code, S message, K meta) {
        Response responseBody = new Response<T, S, K>();
        responseBody.status = false;
        responseBody.code = code;
        responseBody.messages = message;
        responseBody.meta = meta;
        return buildApiResponse(responseBody);
    }

    private <T, S, K> CustomResponse<Response<T, S, K>> buildErrorBaseResponse(long code, S message, K meta) {
        Response responseBody = new Response<T, S, K>();
        responseBody.status = false;
        responseBody.code = (int) code;
        responseBody.messages = message;
        responseBody.meta = meta;
        return buildApiResponse(responseBody);
    }

    private <T, S, K> CustomResponse<Response<T, S, K>> buildErrorBaseResponse(T body, S message, K meta) {
        Response responseBody = new Response<T, S, K>();
        responseBody.status = false;
        responseBody.code = HttpStatus.OK.value();
        responseBody.messages = message;
        responseBody.data = body;
        responseBody.meta = meta;
        return buildApiResponse(responseBody);
    }

    public <T> CustomResponse<Response<T, Message, Meta>> buildErrorResponse(HttpServletRequest request, int code, Message message) {
        Message tmpMessage = getMessages(request, message);
        return buildErrorBaseResponse(code, tmpMessage, null);
    }

    public <T> CustomResponse<Response<T, Message, Meta>> buildErrorResponse(HttpServletRequest request, long code, Message message) {
        Message tmpMessage = getMessages(request, message);
        return buildErrorBaseResponse(code, tmpMessage, null);
    }

    public <T> CustomResponse<Response<T, Message, Meta>> buildErrorResponse(HttpServletRequest request, T body, Message message) {
        Message tmpMessage = getMessages(request, message);
        return buildErrorBaseResponse(body, tmpMessage, null);
    }

    public <T> CustomResponse<Response<T, List<Message>, Meta>> buildListErrorResponse(HttpServletRequest request, int code, List<Message> messages) {
        List<Message> tmpMessage = getMessages(request, messages);
        return buildErrorBaseResponse(code, tmpMessage, null);
    }


    public Message getMessages(HttpServletRequest request, Message message) {
        try {
            String headerAcceptLanguage = request.getHeader(HEADER_ACCEPT_LANGUAGE);
            if (headerAcceptLanguage == null) headerAcceptLanguage = LANGUAGE_VI;
            MessageEntity messageEntity = messageEntityRepository.findById(message.code).orElseThrow(MessageEntityNotFoundException::new);
            // Lấy được ngôn ngữ
            if (message.code != 0) {
                // Chỗ này truy vấn ra message ở trên database, nếu không có thì set là không có dữ liệu chẳng hạn.
                log.info("===========>>>>>>>>>>>>> LANGUAGE: " + headerAcceptLanguage);
                log.info("===========>>>>>>>>>>>>> CODE ERROR: " + message.code);
                log.info("===========>>>>>>>>>>>>> Message : " + messageEntity.getTextVI());
                switch (headerAcceptLanguage) {
                    case LANGUAGE_VI:
                        message.setMessage(messageEntity.getTextVI());
                        break;

                    case LANGUAGE_END:
                        message.setMessage(messageEntity.getTextEN());
                        break;

                    default:
                        message.setMessage(messageEntity.getTextEN());
                        break;
                }

            }

        } catch (Exception e) {
        }
        return message;
    }

    private List<Message> getMessages(HttpServletRequest request, List<Message> messages) {
        if (messages == null || messages.isEmpty()) {
            return null;
        }
        for (int i = 0; i < messages.size(); i++) {
            Message message = messages.get(i);
            // Get Message From Database
            Message newMessage = getMessages(request, message);
            messages.get(i).setCode(newMessage.code);
            messages.get(i).setMessage(newMessage.message);
            messages.get(i).setIntervalMessage(newMessage.intervalMessage);
            messages.get(i).setType(newMessage.type);
        }
        return messages;
    }

}
