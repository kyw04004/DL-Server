package com.nerdnull.donlate.server.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class UserDto {
    private Long id;
    private List<PlanStateDto> planStateList;
    //kakao Coming soon..
}
