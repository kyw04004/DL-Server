package com.nerdnull.donlate.server.mapper;

import com.nerdnull.donlate.server.domain.PlanEntity;
import com.nerdnull.donlate.server.domain.PlanStateEntity;
import com.nerdnull.donlate.server.domain.UserEntity;
import com.nerdnull.donlate.server.dto.PaymentDto;
import com.nerdnull.donlate.server.dto.PlanDto;
import com.nerdnull.donlate.server.dto.PlanStateDto;
import com.nerdnull.donlate.server.dto.UserDto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-12-26T12:55:53+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (Ubuntu)"
)
@Component
public class PlanStateMapperImpl implements PlanStateMapper {

    @Override
    public List<PlanStateDto> toDtoList(List<PlanStateEntity> e) {
        if ( e == null ) {
            return null;
        }

        List<PlanStateDto> list = new ArrayList<PlanStateDto>( e.size() );
        for ( PlanStateEntity planStateEntity : e ) {
            list.add( toDto( planStateEntity ) );
        }

        return list;
    }

    @Override
    public List<PlanStateEntity> toEntityList(List<PlanStateDto> d) {
        if ( d == null ) {
            return null;
        }

        List<PlanStateEntity> list = new ArrayList<PlanStateEntity>( d.size() );
        for ( PlanStateDto planStateDto : d ) {
            list.add( toEntity( planStateDto ) );
        }

        return list;
    }

    @Override
    public PlanStateDto toDto(PlanStateEntity planStateEntity) {
        if ( planStateEntity == null ) {
            return null;
        }

        Long planStateId = null;
        Long planId = null;
        Long userId = null;
        UserDto user = null;
        PlanDto plan = null;
        Integer lateState = null;

        planStateId = planStateEntity.getPlanStateId();
        planId = planStateEntity.getPlanId();
        userId = planStateEntity.getUserId();
        user = userEntityToUserDto( planStateEntity.getUser() );
        plan = planEntityToPlanDto( planStateEntity.getPlan() );
        lateState = planStateEntity.getLateState();

        PlanStateDto planStateDto = new PlanStateDto( planStateId, planId, userId, user, plan, lateState );

        return planStateDto;
    }

    @Override
    public PlanStateEntity toEntity(PlanStateDto planStateDto) {
        if ( planStateDto == null ) {
            return null;
        }

        PlanStateEntity planStateEntity = new PlanStateEntity();

        planStateEntity.setPlanStateId( planStateDto.getPlanStateId() );
        planStateEntity.setPlanId( planStateDto.getPlanId() );
        planStateEntity.setUserId( planStateDto.getUserId() );
        planStateEntity.setLateState( planStateDto.getLateState() );
        planStateEntity.setUser( userDtoToUserEntity( planStateDto.getUser() ) );
        planStateEntity.setPlan( planDtoToPlanEntity( planStateDto.getPlan() ) );

        return planStateEntity;
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

    protected PlanDto planEntityToPlanDto(PlanEntity planEntity) {
        if ( planEntity == null ) {
            return null;
        }

        Long planId = null;
        Long admin = null;
        Long deposit = null;
        Integer latePercent = null;
        Integer absentPercent = null;
        String title = null;
        String location = null;
        String detailLocation = null;
        Date date = null;
        Boolean done = null;

        planId = planEntity.getPlanId();
        admin = planEntity.getAdmin();
        deposit = planEntity.getDeposit();
        latePercent = planEntity.getLatePercent();
        absentPercent = planEntity.getAbsentPercent();
        title = planEntity.getTitle();
        location = planEntity.getLocation();
        detailLocation = planEntity.getDetailLocation();
        date = planEntity.getDate();
        done = planEntity.getDone();

        List<PlanStateDto> planStateList = null;

        PlanDto planDto = new PlanDto( planId, admin, deposit, latePercent, absentPercent, title, location, detailLocation, date, done, planStateList );

        return planDto;
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

    protected PlanEntity planDtoToPlanEntity(PlanDto planDto) {
        if ( planDto == null ) {
            return null;
        }

        PlanEntity planEntity = new PlanEntity();

        planEntity.setPlanId( planDto.getPlanId() );
        planEntity.setAdmin( planDto.getAdmin() );
        planEntity.setDeposit( planDto.getDeposit() );
        planEntity.setLatePercent( planDto.getLatePercent() );
        planEntity.setAbsentPercent( planDto.getAbsentPercent() );
        planEntity.setTitle( planDto.getTitle() );
        planEntity.setLocation( planDto.getLocation() );
        planEntity.setDetailLocation( planDto.getDetailLocation() );
        planEntity.setDate( planDto.getDate() );
        planEntity.setDone( planDto.getDone() );

        return planEntity;
    }
}
