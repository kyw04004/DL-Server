package com.nerdnull.donlate.server.mapper;

import com.nerdnull.donlate.server.domain.PlanEntity;
import com.nerdnull.donlate.server.dto.PlanDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlanMapper extends GenericMapper<PlanDto, PlanEntity>{

    @Mapping(target="planStateList", ignore = true)
    PlanDto toDto(PlanEntity planEntity);
    @Mapping(target="planStateList", ignore = true)
    PlanEntity toEntity(PlanDto planDto);
}