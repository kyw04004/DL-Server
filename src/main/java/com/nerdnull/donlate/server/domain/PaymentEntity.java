package com.nerdnull.donlate.server.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
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

    @Column(nullable = false)
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userId",insertable = false,updatable = false)
    private UserEntity user;
}
