package com.nerdnull.donlate.server.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class PlanStateDto {
    private Long planStateId;
    private UserDto user;
    private PlanDto plan;
    private Integer lateState;//0:출석, 1:지각, 2:결석
    private List<PaymentDto> paymentList;
}
