package com.pro.tuitdormitory.mapper;

import com.pro.tuitdormitory.domain.Admin;
import com.pro.tuitdormitory.dto.request.AdminDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdminMapper {
    AdminMapper getInstance = Mappers.getMapper(AdminMapper.class);

    Admin toEntity(AdminDto adminDto);

    AdminDto toDto(Admin admin);

    Admin updateFromDto(AdminDto adminDto, @MappingTarget Admin admin);

}
