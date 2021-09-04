package com.nerdnull.donlate.server.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PlanDto {
    private Long id;
    private Long admin;
    private Long deposit;
    private Integer latePercent;
    private Integer absentPercent;
    private String title;
    private String location;
    private String detailLocation;
    private Date date;
    private Boolean done;
}
