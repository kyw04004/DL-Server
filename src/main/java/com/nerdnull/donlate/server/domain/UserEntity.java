package com.nerdnull.donlate.server.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long id;

    @OneToMany(mappedBy = "user")
    private List<PlanStateEntity> planStateList = new ArrayList<>();



}
