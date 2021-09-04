package com.nerdnull.donlate.server.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class PlanStateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long planId;

    @Column(nullable = false)
    private Integer lateState;//0:출석, 1:지각, 2:결석
}
