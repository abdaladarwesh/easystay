package com.ntgschool.easystay.Mappers;

import com.ntgschool.easystay.Dtos.Response.UserResponse;
import com.ntgschool.easystay.Entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "location", target = "location")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    UserResponse toUserDto(User user);
}
