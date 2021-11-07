package com.nerdnull.donlate.server.controller.request;

import com.nerdnull.donlate.server.dto.PlanDto;
import com.nerdnull.donlate.server.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePlanStateRequest {
    private Long planStateId;
    private Long planId;
    private Long userId;
    private Integer lateState;//0:출석, 1:지각, 2:결석
}
