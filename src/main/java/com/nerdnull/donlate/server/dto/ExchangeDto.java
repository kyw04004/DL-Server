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
public class ExchangeDto {

    private Long id;
    private String account;
    private Long amount;
    private String nickName;
    private Date requestedAt;

}
