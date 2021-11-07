package com.nerdnull.donlate.server.controller;

import com.nerdnull.donlate.server.controller.request.CreatePlanRequest;
import com.nerdnull.donlate.server.controller.request.CreatePlanStateRequest;
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
import com.nerdnull.donlate.server.controller.response.PlanDetailResponse;
import com.nerdnull.donlate.server.controller.response.Response;
import com.nerdnull.donlate.server.dto.PlanDto;
import com.nerdnull.donlate.server.service.PlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/create")
    public Response<Integer> create(@RequestBody CreatePlanRequest planRequest) {
        try {
            planRequest.isNotNull();
            PlanDto savedPlan = this.planService.setPlan(planRequest);
            log.info("checking getSavedPlanId : {}", savedPlan.getPlanId());
            PlanStateDto planStateDto = new PlanStateDto(null, savedPlan.getPlanId(), savedPlan.getAdmin(), null, null, 0);
            this.planStateService.setPlanState(planStateDto);
        }
        catch (IllegalAccessException e) {
            log.error(e.getMessage(), e);
            return Response.error(Response.BAD_REQUEST, e.getMessage());
        }
        catch (Exception e){
            log.error(e.getMessage(), e);
            return Response.error(Response.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return Response.ok(1);
    }

    @PostMapping("/update")
    public Response<Integer> update(@RequestBody UpdatePlanRequest updatePlanRequest) {
        try {
            updatePlanRequest.isNotNull();
            this.planService.updatePlan(updatePlanRequest);
        }
        catch (IllegalAccessException e){
            log.error(e.getMessage(), e);
            return Response.error(Response.BAD_REQUEST, e.getMessage());
        }
        catch (Exception e){
            log.error(e.getMessage(), e);
            return Response.error(Response.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return Response.ok(1);
    }

    @PostMapping("/delete/{planId}")
    public void delete(@PathVariable Long planId){

        this.planService.deletePlan(planId);
    }

    @PostMapping("/approve")
    public Response<Integer> approve(@RequestBody CreatePlanStateRequest planStateRequest){
        try {
            planStateRequest.isNotNull();
            PlanStateDto planStateDto = new PlanStateDto(null, planStateRequest.getPlanId(), planStateRequest.getUserId(), null,null,0);
            this.planStateService.setPlanState(planStateDto);
        }
        catch (IllegalAccessException e){
            log.error(e.getMessage(), e);
            return Response.error(Response.BAD_REQUEST, e.getMessage());
        }
        catch (Exception e){
            log.error(e.getMessage(), e);
            return Response.error(Response.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return Response.ok(1);
    }

    @GetMapping("/details")
    public Response<PlanDetailResponse> getDetails(@RequestParam Long planId) {
        try {
            PlanDto plan = planService.getDetails(planId);
            log.info("Success send planDetails");
            return Response.ok(new PlanDetailResponse(plan.getPlanId(), plan.getAdmin(), plan.getDeposit(), plan.getLatePercent(), plan.getAbsentPercent(),
                    plan.getTitle(), plan.getLocation(), plan.getDetailLocation(), plan.getDate(), plan.getDone(), plan.getPlanStateList()));
        }
        catch(Exception e) {
            log.error(e.getMessage(), e);
            return Response.error(Response.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
