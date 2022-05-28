package com.nerdnull.donlate.server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class UserDto {

    private Long userId;
    private String nickName;
    private String email;
    private Long point;
    private List<PlanStateDto> planStateList;
    private List<PaymentDto> paymentList;
}
