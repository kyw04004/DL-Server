package com.nerdnull.donlate.server.controller;

import com.nerdnull.donlate.server.controller.response.PlanDetailResponse;
import com.nerdnull.donlate.server.controller.response.Response;
import com.nerdnull.donlate.server.dto.PlanDto;
import com.nerdnull.donlate.server.service.PlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/v1/plan")
@RestController// @Controller + @ResponseBody http 통신규격 + 알아서 json으로 변환해서 리턴시켜줌
@Slf4j
public class PlanController {
    private final PlanService planService;

    @Autowired
    public PlanController(PlanService planService) {
        this.planService = planService;
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
