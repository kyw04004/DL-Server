package com.nerdnull.donlate.server.mapper;

import com.nerdnull.donlate.server.domain.PlanEntity;
import com.nerdnull.donlate.server.dto.PlanDto;
import com.nerdnull.donlate.server.dto.PlanStateDto;
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
public class PlanMapperImpl implements PlanMapper {

    @Override
    public List<PlanDto> toDtoList(List<PlanEntity> e) {
        if ( e == null ) {
            return null;
        }

        List<PlanDto> list = new ArrayList<PlanDto>( e.size() );
        for ( PlanEntity planEntity : e ) {
            list.add( toDto( planEntity ) );
        }

        return list;
    }

    @Override
    public List<PlanEntity> toEntityList(List<PlanDto> d) {
        if ( d == null ) {
            return null;
        }

        List<PlanEntity> list = new ArrayList<PlanEntity>( d.size() );
        for ( PlanDto planDto : d ) {
            list.add( toEntity( planDto ) );
        }

        return list;
    }

    @Override
    public PlanDto toDto(PlanEntity planEntity) {
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

    @Override
    public PlanEntity toEntity(PlanDto planDto) {
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
