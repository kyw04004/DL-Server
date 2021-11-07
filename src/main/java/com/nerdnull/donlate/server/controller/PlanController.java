package com.nerdnull.donlate.server.controller;

import com.nerdnull.donlate.server.controller.request.CreatePlanRequest;
import com.nerdnull.donlate.server.controller.request.UpdatePlanRequest;
import com.nerdnull.donlate.server.domain.PlanEntity;
import com.nerdnull.donlate.server.domain.PlanStateEntity;
import com.nerdnull.donlate.server.dto.PlanDto;
import com.nerdnull.donlate.server.dto.PlanStateDto;
import com.nerdnull.donlate.server.repository.PlanRepository;
import com.nerdnull.donlate.server.service.PlanService;
import com.nerdnull.donlate.server.service.PlanStateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RequestMapping("/api/v1/plan")
@RestController
public class PlanController {

    private final PlanService planService;
    private final PlanStateService planStateService;
    private final PlanRepository planRepository;

    @Autowired
    public PlanController(PlanService planService, PlanStateService planStateService, PlanRepository planRepository) {
        this.planService = planService;
        this.planStateService = planStateService;
        this.planRepository = planRepository;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/create")
    public void create(@RequestBody CreatePlanRequest planRequest) {
        try {
            planRequest.isNotNull();
            PlanDto savedPlan = this.planService.setPlan(planRequest);
            log.info("checking getSavedPlanId : {}", savedPlan.getPlanId());
            PlanStateDto planStateDto = new PlanStateDto(null, savedPlan.getPlanId(), savedPlan.getAdmin(), null, null, 0, null);
            PlanStateDto tmp = this.planStateService.setPlanState(planStateDto);
        }
        catch (IllegalAccessException e) {
            log.error(e.getMessage(), e);
            //return Response.error(Response.BAD_REQUEST, e.getMessage());
        }
        catch (Exception e){
            log.error(e.getMessage(), e);
           // return Response.error(Response.INTERNALSERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping("/update")
    public void update(@RequestBody UpdatePlanRequest updatePlanRequest) throws Exception {
        updatePlanRequest.isNotNull();
        PlanDto updatedPlan = this.planService.updatePlan(updatePlanRequest);
    }

    @PostMapping("/delete/{planId}")
    public void delete(@PathVariable Long planId){

        this.planService.deletePlan(planId);
    }
}
