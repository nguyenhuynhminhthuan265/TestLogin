//package com.test.tbms.service.impl;
//
//import com.test.tbcm.network.exception.notfound.MessageEntityNotFoundException;
//
//import com.test.tbcm.utils.Constants;
//import com.test.tbcm.utils.ModelMapperUtils;
//import com.test.tbms.model.dto.MessageEntityDto;
//import com.test.tbms.model.entity.MessageEntity;
//import com.test.tbms.repository.MessageEntityRepository;
//import com.test.tbms.service.MessageEntityService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Optional;
//
//@Service
//public class MessageEntityServiceImpl implements MessageEntityService {
//
//    @Autowired
//    MessageEntityRepository messageRepository;
//
//    @Override
//    public Page<MessageEntity> get(int page, int offset) throws Exception {
//        CPage newPage = Constants.Page.validate(page, offset);
//        Pageable pageable = PageRequest.of(newPage.getPage(), newPage.getOffset());
//        return messageRepository.findAllByIsDelete(false, pageable);
//
//    }
//
//    @Override
//    public Optional<MessageEntity> findById(long id) throws Exception {
//        return messageRepository.findByIdAndIsDelete(id, false);
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public MessageEntity save(MessageEntityDto dto) throws Exception {
//        if (dto.getId() != null) {
//            if (!this.existsById(dto.getId())) {
//                throw new MessageEntityNotFoundException();
//            }
//        }
//        return this.saveEntity(ModelMapperUtils.toEntity(dto, MessageEntity.class));
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public MessageEntity saveEntity(MessageEntity entity) throws Exception {
//        return messageRepository.save(entity);
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void delete(long id) throws Exception {
//        MessageEntity user = findById(id).orElseThrow(MessageEntityNotFoundException::new);
//        if (user.getId() != null) {
//            if (!this.existsById(user.getId())) {
//                throw new MessageEntityNotFoundException();
//            }
//        }
//        user.setDelete(true);
//        this.saveEntity(user);
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void delete(long id, boolean softDelete) throws Exception {
//
//    }
//
//
//    @Override
//    public Page<MessageEntity> search(String query, int page, int offset) throws Exception {
//        CPage newPage = Constants.Page.validate(page, offset);
//        Pageable pageable = PageRequest.of(newPage.getPage(), newPage.getOffset());
//        Page<MessageEntity> pEntities = messageRepository.findMessagesByTextVIContainingIgnoreCaseOrTextENContainingIgnoreCaseAndIsDelete(pageable, query, query, false);
//        return pEntities;
//    }
//
//    @Override
//    public Boolean existsById(Long id) {
//        return messageRepository.existsById(id);
//    }
//}
