package com.nerdnull.donlate.server.mapper;


import com.nerdnull.donlate.server.domain.UserEntity;
import com.nerdnull.donlate.server.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper extends GenericMapper<UserDto, UserEntity>{
    @Mapping(target="planStateList", ignore = true)
    UserDto toDto(UserEntity userEntity);
    @Mapping(target="planStateList", ignore = true)
    UserEntity toEntity(UserDto userDto);
}
