package com.nerdnull.donlate.server.controller.response;

import com.nerdnull.donlate.server.dto.PlanStateDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class PlanDetailResponse {
    private Long planId;
    private Long admin;
    private Long deposit;
    private Integer latePercent;
    private Integer absentPercent;
    private String title;
    private String location;
    private String detailLocation;
    private Date date;
    private Boolean done;
    private List<PlanStateDto> planStateList;
}
