package com.nerdnull.donlate.server.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.Date;
@Getter
@Entity
public class PlanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
}
