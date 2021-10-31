package com.nerdnull.donlate.server.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class PlanStateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "planStateId")
    private Long planStateId;

    @Column(nullable = false)
    private Integer lateState;//0:출석, 1:지각, 2:결석

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userId",insertable = false,updatable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="planId",insertable = false,updatable = false)
    private PlanEntity plan;

    @OneToMany(mappedBy="planState")
    private List<PaymentEntity> paymentList = new ArrayList<>();


}
