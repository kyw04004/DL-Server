package com.nerdnull.donlate.server.service;

import com.nerdnull.donlate.server.controller.request.CreatePlanRequest;
import com.nerdnull.donlate.server.controller.request.UpdatePlanRequest;
import com.nerdnull.donlate.server.dto.PlanDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class PlanServiceTest {

    @Mock PlanService planService;

    @DisplayName("약속 생성")
    @Test
    void setPlan(){
        //given
        CreatePlanRequest planRequest = new CreatePlanRequest(1L,1L,1,1,
                null,null,null,null,false);
        PlanDto planDto = new PlanDto(1L,1L,1L,1,1,
                null,null,null,null,false,null);
        given(planService.setPlan(planRequest)).willReturn(planDto);

        //when
        PlanDto planDto2 = planService.setPlan(planRequest);

        //then
        Assertions.assertThat(planDto2).isEqualTo(planDto);
        then(planService).should(times(1)).setPlan(planRequest);
    }

    @DisplayName("약속 수정")
    @Test
    void updatePlan(){
        //given
        UpdatePlanRequest updatePlanRequest = new UpdatePlanRequest(1L,1L,1L,1,1,null,null,null,null,false);
        willDoNothing().given(planService).updatePlan(updatePlanRequest);

        //when
        planService.updatePlan(updatePlanRequest);

        //then
        then(planService).should(times(1)).updatePlan(updatePlanRequest);
    }

    @DisplayName("약속 삭제")
    @Test
    void deletePlan(){
        //given
        willDoNothing().given(planService).deletePlan(1L);

        //when
        planService.deletePlan(1L);

        //then
        then(planService).should(times(1)).deletePlan(1L);
    }

    @DisplayName("약속 조회")
    @Test
    void getDetails(){
        //given
        PlanDto planDto = new PlanDto(1L,1L,1L,1,1,
                null,null,null,null,false,null);
        given(planService.getDetails(1L)).willReturn(planDto);

        //when
        PlanDto planDto2 = planService.getDetails(1L);

        //then
        Assertions.assertThat(planDto2).isEqualTo(planDto);
        then(planService).should(times(1)).getDetails(1L);

    }

}
