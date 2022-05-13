package com.nerdnull.donlate.server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class PaymentDto {

    private Long paymentId;
    private Long money;
    private Long point;
    private Date date;
    private Long userId;
    private UserDto user;

}
