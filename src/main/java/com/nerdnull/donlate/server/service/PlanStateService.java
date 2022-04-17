package com.nerdnull.donlate.server.service;

import com.nerdnull.donlate.server.domain.PlanStateEntity;
import com.nerdnull.donlate.server.dto.PlanStateDto;
import com.nerdnull.donlate.server.mapper.PlanStateMapper;
import com.nerdnull.donlate.server.repository.PlanStateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@RequiredArgsConstructor
public class PlanStateService {

    private final PlanStateRepository planStateRepository;
    private final PlanStateMapper planStateMapper;

    @Transactional
    public void setPlanState(PlanStateDto planStateDto) throws IllegalAccessException {
        PlanStateEntity planStateEntity =
                this.planStateRepository.findByUserIdAndPlanId(planStateDto.getUserId(), planStateDto.getPlanId());
        if(planStateEntity != null)
            planStateEntity.setLateState(planStateDto.getLateState());
        else planStateRepository.save(planStateMapper.toEntity(planStateDto));
    }

    public void deleteByUserId(Long userId) {
        this.planStateRepository.deleteAllByUserId(userId);
    }

    public void deleteByPlanId(Long planId) {
        planStateRepository.deleteAllByPlanId(planId);
    }
}