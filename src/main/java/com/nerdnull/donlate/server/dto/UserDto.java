package com.nerdnull.donlate.server.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class UserDto {
    private Long userId;
    private String nickName;
    private String email;
    private Long point;
    private List<PlanStateDto> planStateList;
}
