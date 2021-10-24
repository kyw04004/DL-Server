package com.nerdnull.donlate.server.dto;

import com.nerdnull.donlate.server.domain.PlanEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Data
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
