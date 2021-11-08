package com.nerdnull.donlate.server.mapper;

import com.nerdnull.donlate.server.domain.PaymentEntity;
import com.nerdnull.donlate.server.dto.PaymentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentMapper extends GenericMapper<PaymentDto, PaymentEntity> {
    @Mapping(target="user.planStateList", ignore = true)
    @Mapping(target="user.paymentList", ignore = true)
    PaymentDto toDto(PaymentEntity paymentEntity);
    @Mapping(target="user.planStateList", ignore = true)
    @Mapping(target="user.paymentList", ignore = true)
    PaymentEntity toEntity(PaymentDto paymentDto);
}