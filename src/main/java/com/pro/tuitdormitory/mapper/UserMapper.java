package com.pro.tuitdormitory.mapper;

import com.pro.tuitdormitory.domain.User;
import com.pro.tuitdormitory.dto.request.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper getInstance = Mappers.getMapper(UserMapper.class);

    User toEntity(UserDto userDto);
    UserDto toDto(User user);

    User updateFromDto(UserDto userDto, @MappingTarget User user);

}
