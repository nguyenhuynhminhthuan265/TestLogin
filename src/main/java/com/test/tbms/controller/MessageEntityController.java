//package com.test.tbms.controller;
//
//import com.test.tbcm.config.Route;
//import com.test.tbcm.network.*;
//import com.test.tbcm.network.exception.notfound.MessageEntityNotFoundException;
//
//import com.test.tbcm.utils.ModelMapperUtils;
//import com.test.tbms.model.dto.MessageEntityDto;
//import com.test.tbms.model.entity.MessageEntity;
//import com.test.tbms.service.impl.MessageEntityServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.List;
//
//@RestController
//@RequestMapping(Route.V1.prefixApi)
//public class MessageEntityController {
//    @Autowired
//    HttpServletRequest request;
//
//    @Autowired
//    MessageEntityServiceImpl messageEntityServiceImpl;
//
//    @Autowired
//    ResponseUtil responseUtil;
//
//    //API: S22.0
//    @GetMapping(Route.V1.GET_MESSAGE)
//    public CustomResponse<Response<List<MessageEntityDto>, Message, Meta>> getListMessage(@RequestParam(value = "page") int page,
//                                                                                          @RequestParam(value = "offset") int offset) throws Exception {
//        Message message = new Message();
//        Page<MessageEntity> pEntities = messageEntityServiceImpl.get(page, offset);
//        Meta meta = new Meta(page, pEntities.getTotalPages(), pEntities.getTotalElements());
//        message.setCode(MessagesUtils.MESSAGE_GET_SUCCESS);
//        return responseUtil.buildSuccessResponse(request, ModelMapperUtils.toListDto(pEntities.getContent(), MessageEntityDto.class), message, meta);
//
//    }
//
//    // API: S22.1
//    @GetMapping(Route.V1.FIND_MESSAGE_BY_ID)
//    public CustomResponse<Response<MessageEntityDto, Message, Meta>> findById(@PathVariable("id") long id) throws Exception {
//        Message message = new Message();
//        MessageEntity entity = messageEntityServiceImpl.findById(id).orElseThrow(MessageEntityNotFoundException::new);
//        message.setCode(MessagesUtils.MESSAGE_GET_SUCCESS);
//        return responseUtil.buildSuccessResponse(request, ModelMapperUtils.toDto(entity, MessageEntityDto.class), message);
//    }
//
//    //API: S22.2
//    @PostMapping(Route.V1.CREATE_MESSAGE)
//    public CustomResponse<Response<MessageEntityDto, Message, Meta>> create(@RequestBody MessageEntityDto dto) throws Exception {
//        dto.setId(null);
//        Message message = new Message();
//        MessageEntity entity = messageEntityServiceImpl.save(dto);
//        message.setCode(MessagesUtils.MESSAGE_CREATE_SUCCESS);
//        return responseUtil.buildSuccessResponse(request, ModelMapperUtils.toDto(entity, MessageEntityDto.class), message);
//    }
//
//    //API: S22.3
//    @PutMapping(Route.V1.UPDATE_MESSAGE)
//    public CustomResponse<Response<MessageEntityDto, Message, Meta>> update(@PathVariable("id") long id, @RequestBody MessageEntityDto dto) throws Exception {
//        Message message = new Message();
//        dto.setId(id);
//        MessageEntity entity = messageEntityServiceImpl.save(dto);
//        message.setCode(MessagesUtils.MESSAGE_UPDATE_SUCCESS);
//        return responseUtil.buildSuccessResponse(request, ModelMapperUtils.toDto(entity, MessageEntityDto.class), message);
//    }
//
//    //API: S22.4
//    @DeleteMapping(Route.V1.DELETE_MESSAGE_BY_ID)
//    public CustomResponse<Response<MessageEntityDto, Message, Meta>> deleteById(@PathVariable("id") long id) throws Exception {
//        Message message = new Message();
//        MessageEntity entity = messageEntityServiceImpl.findById(id).orElseThrow(MessageEntityNotFoundException::new);
//        messageEntityServiceImpl.delete(id);
//        message.setCode(MessagesUtils.MESSAGE_DELETE_SUCCESS);
//        return responseUtil.buildSuccessResponse(request, ModelMapperUtils.toDto(entity, MessageEntityDto.class), message);
//    }
//
//    //API: S22.5
//    @GetMapping(Route.V1.FIND_MESSAGE_BY_TEXT)
//    public CustomResponse<Response<List<MessageEntityDto>, Message, Meta>> findMessageByName(@RequestParam("keyword") String keyword,
//                                                                                             @RequestParam(value = "page") int page,
//                                                                                             @RequestParam(value = "offset") int offset) throws Exception {
//        Message message = new Message();
//        Page<MessageEntity> pEntities = messageEntityServiceImpl.search(keyword, page, offset);
//        Meta meta = new Meta(page, pEntities.getTotalPages(), pEntities.getTotalElements());
//        message.setCode(MessagesUtils.MESSAGE_GET_SUCCESS);
//        return responseUtil.buildSuccessResponse(request, ModelMapperUtils.toListDto(pEntities.getContent(), MessageEntityDto.class), message, meta);
//
//    }
//}
