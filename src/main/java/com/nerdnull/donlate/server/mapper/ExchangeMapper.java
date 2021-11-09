package com.nerdnull.donlate.server.mapper;

import com.nerdnull.donlate.server.domain.ExchangeEntity;
import com.nerdnull.donlate.server.dto.ExchangeDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExchangeMapper extends GenericMapper<ExchangeDto, ExchangeEntity> {
    ExchangeDto toDto(ExchangeEntity exchangeEntity);
    ExchangeEntity toEntity(ExchangeDto exchangeDto);
}
