package com.nerdnull.donlate.server.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PaymentDto {
    private Long paymentId;
    private Long amount;
    private Date date;
    private PlanStateDto planState;
}
