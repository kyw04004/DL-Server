package com.nerdnull.donlate.server.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
public class PlanStateDto {

    private Long planStateId;
    private Long planId;
    private Long userId;
    private UserDto user;
    private PlanDto plan;
    private Integer lateState;//0:출석, 1:지각, 2:결석

}
