package com.nerdnull.donlate.server.controller.response;

import com.nerdnull.donlate.server.dto.PaymentDto;
import com.nerdnull.donlate.server.dto.PlanStateDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserDetailResponse {
    private Long userId;
    private String nickName;
    private String email;
    private Long point;
    private List<PlanStateDto> planStateList;
    private List<PaymentDto> paymentList;
}
