package com.nerdnull.donlate.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllocateDto {
    private List<PlanStateDto> planStateList;
    private Long deposit;
    private Long toNormal;
    private int normalCnt;
}
