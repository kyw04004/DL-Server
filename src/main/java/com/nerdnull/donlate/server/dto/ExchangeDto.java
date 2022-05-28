package com.nerdnull.donlate.server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class ExchangeDto {

    private Long exchangeId;
    private String bank;
    private String account;
    private Long amount;
    private String name;
    private Date requestedAt;

}
