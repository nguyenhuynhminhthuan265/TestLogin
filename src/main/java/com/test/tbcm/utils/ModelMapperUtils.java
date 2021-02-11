package com.test.tbcm.utils;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ModelMapperUtils {

//    @Autowired
//    @Qualifier("modelMapper")
//    public static ModelMapper modelMapper;

    public static <T, K> List<K> toListDto(List<T> listEntity, Class<K> dtoClass) {
        if (listEntity == null || listEntity.isEmpty()) {
            return Collections.emptyList();
        }
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        List<K> listDto = listEntity
                .stream()
                .map(it -> modelMapper.map(it, dtoClass))
                .collect(Collectors.toList());
        return listDto;
    }

    public static <T, K> List<T> toListEntity(List<K> listDto, Class<T> entityClass) {
        if (listDto == null || listDto.isEmpty()) {
            return Collections.emptyList();
        }
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        List<T> listEntity = listDto
                .stream()
                .map(it -> modelMapper.map(it, entityClass))
                .collect(Collectors.toList());
        return listEntity;
    }


    public static <T, K> K toDto(T entity, Class<K> dtoClass) {
        if (entity == null) {
            return null;
        }
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper.map(entity, dtoClass);
    }

    public static <T, K> T toEntity(K dto, Class<T> entityClass) {
        if (dto == null) {
            return null;
        }
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper.map(dto, entityClass);
    }

}
