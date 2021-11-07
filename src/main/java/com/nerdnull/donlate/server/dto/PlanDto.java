package com.nerdnull.donlate.server.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class PlanDto {

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
