package com.nerdnull.donlate.server.service;

import com.nerdnull.donlate.server.dto.PlanStateDto;
import com.nerdnull.donlate.server.mapper.PlanStateMapper;
import com.nerdnull.donlate.server.repository.PlanStateRepository;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class PlanStateService {

    private final PlanStateRepository planStateRepository;
    private final PlanStateMapper planStateMapper = Mappers.getMapper(PlanStateMapper.class);

    @Autowired
    public PlanStateService(PlanStateRepository planStateRepository) {
        this.planStateRepository = planStateRepository;
    }

    public void setPlanState(PlanStateDto planStateDto) {
        planStateRepository.save(planStateMapper.toEntity(planStateDto));
    }

    public void deleteByUserId(Long userId) {
        this.planStateRepository.deleteAllByUserId(userId);
    }

    public void deleteByPlanId(Long planId) {
        planStateRepository.deleteAllByPlanId(planId);
    }
}