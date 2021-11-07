package com.nerdnull.donlate.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class PaymentDto {
    private Long paymentId;
    private Long amount;
    private Date date;
    private Long userId;
    private UserDto user;
}
