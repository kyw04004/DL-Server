package com.nerdnull.donlate.server.controller;

import com.nerdnull.donlate.server.controller.request.CreatePlanRequest;
import com.nerdnull.donlate.server.controller.request.JoinRequest;
import com.nerdnull.donlate.server.controller.request.UpdatePlanRequest;
import com.nerdnull.donlate.server.dto.CalculateParseDto;
import com.nerdnull.donlate.server.dto.PaymentDto;
import com.nerdnull.donlate.server.dto.PlanDto;
import com.nerdnull.donlate.server.dto.PlanStateDto;
import com.nerdnull.donlate.server.parse.CalculateParse;
import com.nerdnull.donlate.server.service.PaymentService;
import com.nerdnull.donlate.server.service.PlanService;
import com.nerdnull.donlate.server.service.PlanStateService;
import com.nerdnull.donlate.server.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.nerdnull.donlate.server.controller.response.PlanDetailResponse;
import com.nerdnull.donlate.server.controller.response.Response;


import java.util.Date;
import java.util.List;

@Slf4j
@RequestMapping("/api/v1/plan")
@RestController
public class PlanController {

    private final PlanService planService;
    private final PlanStateService planStateService;
    private final PaymentService paymentService;
    private final UserService userService;

    @Autowired
    public PlanController(PlanService planService, PlanStateService planStateService, PaymentService paymentService, UserService userService) {
        this.planService = planService;
        this.planStateService = planStateService;
        this.paymentService = paymentService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public Response<Boolean> create(@RequestBody CreatePlanRequest planRequest) {
        try {
            planRequest.isNotNull();
            planRequest.checkDeposit();
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
        return Response.ok(true);
    }

    @PutMapping("/update")
    public Response<Boolean> update(@RequestBody UpdatePlanRequest updatePlanRequest) {
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
        return Response.ok(true);
    }

    @DeleteMapping(value = "/delete/{planId}")
    public Response<String> delete(@PathVariable("planId") Long planId){
        try {
            if(planId==null) throw new IllegalArgumentException("planId could not be null");
            this.planStateService.deleteByPlanId(planId);
            this.planService.deletePlan(planId);
        }
        catch (IllegalArgumentException e){
            log.error(e.getMessage(), e);
            return Response.error(Response.BAD_REQUEST, e.getMessage());
        }
        catch (Exception e){
            log.error(e.getMessage(), e);
            return Response.error(Response.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return Response.ok("delete complete!!");
    }

    @PostMapping("/join")
    public Response<Boolean> join(@RequestBody JoinRequest request){
        try {
            request.isNotNull();
            PlanStateDto planStateDto = new PlanStateDto(null, request.getPlanId(), request.getUserId(), null,null,0);
            this.planStateService.setPlanState(planStateDto);

            this.userService.updatePoint(request.getUserId(), -request.getPoint());

            PaymentDto payment = new PaymentDto(null, -request.getMoney(), -request.getPoint(), new Date(), request.getUserId(), null);
            this.paymentService.add(payment);
        }
        catch (IllegalAccessException e){
            log.error(e.getMessage(), e);
            return Response.error(Response.BAD_REQUEST, e.getMessage());
        }
        catch (Exception e){
            log.error(e.getMessage(), e);
            return Response.error(Response.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return Response.ok(true);
    }

    @PutMapping("/calculate")
    public Response<Boolean>calculate(@RequestBody String body){
        try{
            CalculateParseDto cal = CalculateParse.parse(body);
            PlanDto plan = this.planService.getDetails(cal.getPlanId());
            List<PlanStateDto> planStateList = plan.getPlanStateList();
            for (PlanStateDto p : planStateList) {
                this.planStateService.setPlanState(new PlanStateDto(p.getPlanStateId(),cal.getPlanId(),
                        p.getUserId(),null,null,cal.getUserState().get(p.getUserId())));
            }
        }
        catch (Exception e){
            log.error(e.getMessage(), e);
            return Response.error(Response.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return Response.ok(true);
    }

    /**
     *
     * PLAN ID로 PLAN 상세 정보 요청
     *
     * @param planId input(plan ID)
     * @return Plan Info
     */
    @GetMapping("/details/{planId}")
    public Response<PlanDetailResponse> getDetails(@PathVariable("planId") Long planId) {
        try {
            if(planId==null) throw new IllegalArgumentException("planId could not be null");
            PlanDto plan = planService.getDetails(planId);
            log.info("Success send planDetails");
            return Response.ok(new PlanDetailResponse(plan.getPlanId(), plan.getAdmin(), plan.getDeposit(), plan.getLatePercent(), plan.getAbsentPercent(),
                    plan.getTitle(), plan.getLocation(), plan.getDetailLocation(), plan.getDate(), plan.getDone(), plan.getPlanStateList()));
        }
        catch (IllegalArgumentException e){
            log.error(e.getMessage(), e);
            return Response.error(Response.BAD_REQUEST, e.getMessage());
        }
        catch(Exception e) {
            log.error(e.getMessage(), e);
            return Response.error(Response.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}