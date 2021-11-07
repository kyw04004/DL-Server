package com.nerdnull.donlate.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@AllArgsConstructor
public class UserDto {

    private Long userId;
    private String nickName;
    private String email;
    private Long point;
    private List<PlanStateDto> planStateList;
    private List<PaymentDto> paymentList;
}
