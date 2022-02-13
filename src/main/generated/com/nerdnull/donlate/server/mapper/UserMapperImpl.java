package com.nerdnull.donlate.server.mapper;

import com.nerdnull.donlate.server.domain.UserEntity;
import com.nerdnull.donlate.server.dto.PaymentDto;
import com.nerdnull.donlate.server.dto.PlanStateDto;
import com.nerdnull.donlate.server.dto.UserDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-01-27T20:29:20+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.13 (Ubuntu)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public List<UserDto> toDtoList(List<UserEntity> e) {
        if ( e == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( e.size() );
        for ( UserEntity userEntity : e ) {
            list.add( toDto( userEntity ) );
        }

        return list;
    }

    @Override
    public List<UserEntity> toEntityList(List<UserDto> d) {
        if ( d == null ) {
            return null;
        }

        List<UserEntity> list = new ArrayList<UserEntity>( d.size() );
        for ( UserDto userDto : d ) {
            list.add( toEntity( userDto ) );
        }

        return list;
    }

    @Override
    public UserDto toDto(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        Long userId = null;
        String nickName = null;
        String email = null;
        Long point = null;

        userId = userEntity.getUserId();
        nickName = userEntity.getNickName();
        email = userEntity.getEmail();
        point = userEntity.getPoint();

        List<PlanStateDto> planStateList = null;
        List<PaymentDto> paymentList = null;

        UserDto userDto = new UserDto( userId, nickName, email, point, planStateList, paymentList );

        return userDto;
    }

    @Override
    public UserEntity toEntity(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setUserId( userDto.getUserId() );
        userEntity.setNickName( userDto.getNickName() );
        userEntity.setEmail( userDto.getEmail() );
        userEntity.setPoint( userDto.getPoint() );

        return userEntity;
    }
}
