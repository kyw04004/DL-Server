package com.nerdnull.donlate.server.controller;

import com.nerdnull.donlate.server.dto.PlanDto;
import com.nerdnull.donlate.server.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/plan")
public class PlanController {

    private PlanService planService;

    @Autowired
    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @ResponseStatus(value= HttpStatus.CREATED)
    @PostMapping("/create")
    public void create(){
       // this.planService.setDetails();

    }
    @PostMapping("/approve")
    public void approve(){
        // 수락여부 관련
        // 약속현황 업데아트
    }


}
