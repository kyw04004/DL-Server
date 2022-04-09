package com.nerdnull.donlate.server.domain;

import com.nerdnull.donlate.server.dto.LateState;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class PlanStateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "planStateId")
    private Long planStateId;

    @Column(nullable = false)
    private Long planId;

    @Column(nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LateState lateState;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userId", insertable = false, updatable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="planId", insertable = false, updatable = false)
    private PlanEntity plan;

}
