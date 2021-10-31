package com.nerdnull.donlate.server.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.Date;
@Getter
@Entity
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paymentId")
    private Long paymentId;

    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false)
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="planStateId",insertable = false,updatable = false)
    private PlanStateEntity planState;


}
