package com.nerdnull.donlate.server.mapper;

import com.nerdnull.donlate.server.domain.PlanStateEntity;
import com.nerdnull.donlate.server.dto.PlanStateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlanStateMapper extends GenericMapper<PlanStateDto, PlanStateEntity>{

    @Mapping(target="paymentList", ignore = true)
    PlanStateDto toDto(PlanStateEntity planStateEntity);
    @Mapping(target="paymentList", ignore = true)
    PlanStateEntity toEntity(PlanStateDto planStateDto);

}