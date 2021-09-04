package com.nerdnull.donlate.server.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PlanStateDto {
    private Long id;
    private Long userId;
    private Long planId;
    private Integer lateState;//0:출석, 1:지각, 2:결석
}
