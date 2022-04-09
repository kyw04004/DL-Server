package com.nerdnull.donlate.server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Builder
@AllArgsConstructor
public class PlanStateDto {

    private Long planStateId;
    private Long planId;
    private Long userId;
    private UserDto user;
    private PlanDto plan;
    @Enumerated(EnumType.STRING)
    private LateState lateState;

}
