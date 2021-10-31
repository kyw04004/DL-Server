package com.nerdnull.donlate.server.service;

import com.nerdnull.donlate.server.mapper.PlanMapper;
import com.nerdnull.donlate.server.mapper.PlanStateMapper;
import com.nerdnull.donlate.server.domain.PlanEntity;
import com.nerdnull.donlate.server.dto.PlanDto;
import com.nerdnull.donlate.server.repository.PlanRepository;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class PlanService {

    private final PlanRepository planRepository;
    private final PlanMapper planMapper = Mappers.getMapper(PlanMapper.class);
    private final PlanStateMapper planStateMapper = Mappers.getMapper(PlanStateMapper.class);

    @Autowired
    public PlanService(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    public PlanDto getDetails(Long planId) {
        PlanEntity maybePlan = planRepository.findById(planId).orElseThrow(() -> new IllegalArgumentException("Not exists plan"));
        PlanDto plan = planMapper.toDto(maybePlan);
        plan.setPlanStateList(planStateMapper.toDtoList(maybePlan.getPlanStateList()));
        return plan;
    }
}
