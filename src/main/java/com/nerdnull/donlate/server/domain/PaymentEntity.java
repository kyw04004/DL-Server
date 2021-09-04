package com.nerdnull.donlate.server.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.Date;
@Getter
@Entity
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false)
    private Long planStateId;

    @Column(nullable = false)
    private Date date;
}
