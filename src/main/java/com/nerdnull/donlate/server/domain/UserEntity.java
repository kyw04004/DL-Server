package com.nerdnull.donlate.server.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long userId;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Long point;

    @OneToMany(mappedBy = "user")
    private List<PlanStateEntity> planStateList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<PaymentEntity> paymentList = new ArrayList<>();
}
