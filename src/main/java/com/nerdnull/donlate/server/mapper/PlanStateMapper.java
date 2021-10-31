package com.nerdnull.donlate.server.mapper;

import com.nerdnull.donlate.server.domain.PlanStateEntity;
import com.nerdnull.donlate.server.dto.PlanStateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlanStateMapper extends GenericMapper<PlanStateDto, PlanStateEntity>{

    @Mapping(target="paymentList", ignore = true)
    @Mapping(target="user.planStateList", ignore = true)
    @Mapping(target="plan.planStateList", ignore = true)
    PlanStateDto toDto(PlanStateEntity planStateEntity);
    @Mapping(target="paymentList", ignore = true)
    @Mapping(target="user.planStateList", ignore = true)
    @Mapping(target="plan.planStateList", ignore = true)
    PlanStateEntity toEntity(PlanStateDto planStateDto);
}