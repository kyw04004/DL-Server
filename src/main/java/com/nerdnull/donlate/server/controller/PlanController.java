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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import com.nerdnull.donlate.server.controller.response.PlanDetailResponse;
import com.nerdnull.donlate.server.controller.response.Response;


import java.util.Date;
import java.util.List;

@Slf4j
@RequestMapping("/api/v1/plan")
@RestController
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;
    private final PlanStateService planStateService;
    private final PaymentService paymentService;
    private final UserService userService;

    /**
     *
     * 약속관련 정보 받아서 plan 생성
     *
     * @param planRequest input(admin ID, deposit, latePercent, absentPercent, title, location, detailLocation, date, done)
     * @return String message
     */
    @PostMapping("/create")
    public Response<String> create(@RequestBody CreatePlanRequest planRequest) {
        try {
            planRequest.isNotNull();
            planRequest.checkDeposit();
            PlanDto savedPlan = this.planService.setPlan(planRequest);
            log.info("checking getSavedPlanId : {}", savedPlan.getPlanId());
            PlanStateDto planStateDto = PlanStateDto.builder()
                    .planId(savedPlan.getPlanId())
                    .userId(savedPlan.getAdmin())
                    .lateState(0)
                    .build();
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
        return Response.ok("create plan complete!!");
    }

    /**
     *
     * plan 수정사항 받아서 약속 정보 update
     *
     * @param updatePlanRequest input(plan ID, admin ID, deposit, latePercent, absentPercent, title, location, detailLocation, date, done)
     * @return String message
     */
    @PutMapping("/update")
    public Response<String> update(@RequestBody UpdatePlanRequest updatePlanRequest) {
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
        return Response.ok("update plan complete");
    }

    /**
     *
     * 해당 planId 를 가진 plan 을 planStateList 와 planList 에서 삭제
     *
     * @param planId input(plan ID)
     * @return String message
     */
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

    /**
     *
     * plan 에 참여하는 인원들 planStateList 에 추가, 각 인원별 point 정보 갱신
     *
     * @param request input(plan ID, user ID, point, money)
     * @return String message
     */
    @PostMapping("/join")
    public Response<String> join(@RequestBody JoinRequest request){
        try {
            request.isNotNull();
            PlanStateDto planStateDto = PlanStateDto.builder()
                    .planId(request.getPlanId())
                    .userId(request.getUserId())
                    .lateState(0)
                    .build();
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
        return Response.ok("join complete!!");
    }

    /**
     *
     * plan 에 참여한 user 들의 lateState 를 참고하여 정산
     *
     * @param body input(plan ID, userState<user ID, lateState>)
     * @return String message
     */
    @PutMapping("/calculate")
    public Response<String>calculate(@RequestBody String body){
        try{
            CalculateParseDto cal = CalculateParse.parse(body);
            PlanDto plan = this.planService.getDetails(cal.getPlanId());
            List<PlanStateDto> planStateList = plan.getPlanStateList();
            for (PlanStateDto p : planStateList) {
                this.planStateService.setPlanState(PlanStateDto.builder()
                                .planStateId(p.getPlanStateId())
                                .planId(cal.getPlanId())
                                .userId(p.getUserId())
                                .lateState(cal.getUserState().get(p.getUserId()))
                                .build());
            }
        }
        catch (Exception e){
            log.error(e.getMessage(), e);
            return Response.error(Response.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return Response.ok("calculate complete!!");
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
            if(planId == null) throw new IllegalArgumentException("planId could not be null");
            PlanDto plan = planService.getDetails(planId);
            log.info("Success send planDetails");
            return Response.ok(PlanDetailResponse.builder()
                    .planId(plan.getPlanId())
                    .admin(plan.getAdmin())
                    .deposit(plan.getDeposit())
                    .latePercent(plan.getLatePercent())
                    .absentPercent(plan.getAbsentPercent())
                    .title(plan.getTitle())
                    .location(plan.getLocation())
                    .detailLocation(plan.getDetailLocation())
                    .date(plan.getDate())
                    .done(plan.getDone())
                    .planStateList(plan.getPlanStateList())
                    .build());
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