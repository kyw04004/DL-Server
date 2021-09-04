package com.nerdnull.donlate.server.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PaymentDto {
    private Long id;
    private Long amount;
    private Long planStateId;
    private Date date;
}
