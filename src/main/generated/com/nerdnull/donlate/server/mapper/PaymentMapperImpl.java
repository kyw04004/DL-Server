package com.nerdnull.donlate.server.mapper;

import com.nerdnull.donlate.server.domain.PaymentEntity;
import com.nerdnull.donlate.server.domain.UserEntity;
import com.nerdnull.donlate.server.dto.PaymentDto;
import com.nerdnull.donlate.server.dto.PlanStateDto;
import com.nerdnull.donlate.server.dto.UserDto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-01-27T20:29:20+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.13 (Ubuntu)"
)
@Component
public class PaymentMapperImpl implements PaymentMapper {

    @Override
    public List<PaymentDto> toDtoList(List<PaymentEntity> e) {
        if ( e == null ) {
            return null;
        }

        List<PaymentDto> list = new ArrayList<PaymentDto>( e.size() );
        for ( PaymentEntity paymentEntity : e ) {
            list.add( toDto( paymentEntity ) );
        }

        return list;
    }

    @Override
    public List<PaymentEntity> toEntityList(List<PaymentDto> d) {
        if ( d == null ) {
            return null;
        }

        List<PaymentEntity> list = new ArrayList<PaymentEntity>( d.size() );
        for ( PaymentDto paymentDto : d ) {
            list.add( toEntity( paymentDto ) );
        }

        return list;
    }

    @Override
    public PaymentDto toDto(PaymentEntity paymentEntity) {
        if ( paymentEntity == null ) {
            return null;
        }

        Long paymentId = null;
        Long money = null;
        Long point = null;
        Date date = null;
        Long userId = null;
        UserDto user = null;

        paymentId = paymentEntity.getPaymentId();
        money = paymentEntity.getMoney();
        point = paymentEntity.getPoint();
        date = paymentEntity.getDate();
        userId = paymentEntity.getUserId();
        user = userEntityToUserDto( paymentEntity.getUser() );

        PaymentDto paymentDto = new PaymentDto( paymentId, money, point, date, userId, user );

        return paymentDto;
    }

    @Override
    public PaymentEntity toEntity(PaymentDto paymentDto) {
        if ( paymentDto == null ) {
            return null;
        }

        PaymentEntity paymentEntity = new PaymentEntity();

        paymentEntity.setPaymentId( paymentDto.getPaymentId() );
        paymentEntity.setMoney( paymentDto.getMoney() );
        paymentEntity.setPoint( paymentDto.getPoint() );
        paymentEntity.setDate( paymentDto.getDate() );
        paymentEntity.setUserId( paymentDto.getUserId() );
        paymentEntity.setUser( userDtoToUserEntity( paymentDto.getUser() ) );

        return paymentEntity;
    }

    protected UserDto userEntityToUserDto(UserEntity userEntity) {
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

    protected UserEntity userDtoToUserEntity(UserDto userDto) {
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
