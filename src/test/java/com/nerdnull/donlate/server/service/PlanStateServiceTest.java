package com.nerdnull.donlate.server.service;

import com.nerdnull.donlate.server.dto.PlanStateDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class PlanStateServiceTest {

    @Mock PlanStateService planStateService;

    @DisplayName("약속 현황 생성 성공")
    @Test
    void setPlanStateSuccess() throws IllegalAccessException{
        //given
        PlanStateDto planStateDto = new PlanStateDto(1L, 1L, 1L, null, null, null);
        willDoNothing().given(planStateService).setPlanState(planStateDto);

        //when
        planStateService.setPlanState(planStateDto);

        //then
        then(planStateService).should(times(1)).setPlanState(planStateDto);
    }

    @DisplayName("약속 현황 생성 실패")
    @Test
    void setPlanStateFail() throws IllegalAccessException{
        //given
        PlanStateDto planStateDto = new PlanStateDto(1L, 1L, 1L, null, null, null);
        willThrow(new IllegalAccessException()).given(planStateService).setPlanState(planStateDto);

        //when
        try {
            planStateService.setPlanState(planStateDto);
        } catch (IllegalAccessException e) {
            return;
        }

        //then
        Assertions.fail("약속 현황 중복으로 예외가 발생해야 한다.");
    }

    @DisplayName("해당 유저의 모든 약속 현황 삭제")
    @Test
    void deleteByUserId() {
        //given
        willDoNothing().given(planStateService).deleteByUserId(1L);

        //when
        planStateService.deleteByUserId(1L);

        //then
        then(planStateService).should(times(1)).deleteByUserId(1L);
    }

    @DisplayName("해당 약속의 모든 약속 현황 삭제")
    @Test
    void deleteByPlanId() {
        //given
        willDoNothing().given(planStateService).deleteByPlanId(1L);

        //when
        planStateService.deleteByPlanId(1L);

        //then
        then(planStateService).should(times(1)).deleteByPlanId(1L);
    }
}