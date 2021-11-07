package com.nerdnull.donlate.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Data
@AllArgsConstructor
public class PaymentDto {

    private Long paymentId;
    private Long amount;
    private PlanStateDto planState;
    private Date date;

}
