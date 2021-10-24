package com.nerdnull.donlate.server.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Entity
public class ExchangeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exchangeId")
    private Long id;

    @Column(nullable = false)
    private String account;

    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    private Date requestedAt;
}
