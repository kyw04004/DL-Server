package com.nerdnull.donlate.server.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Entity
public class PlanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "planId")
    private Long planId;

    @Column(nullable = false)
    private Long admin;

    @Column(nullable = false)
    private Long deposit;

    @Column(nullable = false)
    private Integer latePercent;

    @Column(nullable = false)
    private Integer absentPercent;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String detailLocation;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private Boolean done;

    @OneToMany(mappedBy = "plan")
    private List<PlanStateEntity> planStateList = new ArrayList<>();
}
