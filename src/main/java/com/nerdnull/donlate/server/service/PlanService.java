package com.nerdnull.donlate.server.service;

import com.nerdnull.donlate.server.controller.request.CreatePlanRequest;
import com.nerdnull.donlate.server.controller.request.UpdatePlanRequest;
import com.nerdnull.donlate.server.domain.PlanEntity;
import com.nerdnull.donlate.server.dto.PlanDto;
import com.nerdnull.donlate.server.mapper.PlanMapper;
import com.nerdnull.donlate.server.mapper.PlanStateMapper;
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

    public PlanDto setPlan(CreatePlanRequest planRequest) {
        try {
            PlanDto planDto = new PlanDto(null, planRequest.getAdmin(), planRequest.getDeposit(),
                    planRequest.getLatePercent(), planRequest.getAbsentPercent(), planRequest.getTitle(),
                    planRequest.getLocation(), planRequest.getDetailLocation(), planRequest.getDate(),
                    planRequest.getDone(), null);
            PlanEntity saved = planRepository.save(planMapper.toEntity(planDto));
            return planMapper.toDto(saved);
        }
        catch (Exception e){
            throw e;
        }
    }

    public void updatePlan(UpdatePlanRequest updatePlanRequest){
        try {
            PlanDto planDto = new PlanDto(updatePlanRequest.getPlanId(), updatePlanRequest.getAdmin(), updatePlanRequest.getDeposit(),
                    updatePlanRequest.getLatePercent(), updatePlanRequest.getAbsentPercent(), updatePlanRequest.getTitle(),
                    updatePlanRequest.getLocation(), updatePlanRequest.getDetailLocation(), updatePlanRequest.getDate(),
                    updatePlanRequest.getDone(), null);

            Optional<PlanEntity> target = planRepository.findById(planDto.getPlanId());

            if (target.isEmpty()) {
                throw new IllegalArgumentException("Plan is not exist, can't update");
            }

            PlanEntity saved = planRepository.save(planMapper.toEntity(planDto));

            planMapper.toDto(saved);
        }
        catch (Exception e){
            throw e;
        }
    }

    public void deletePlan(Long planId) {

            Optional<PlanEntity> target = planRepository.findById(planId);

            if (target.isEmpty()) {
                throw new IllegalArgumentException("Plan is not exist, can't delete");
            }

            planRepository.deleteById(planId);
    }

    public PlanDto getDetails(Long planId) {
        PlanEntity maybePlan = planRepository.findById(planId).orElseThrow(() -> new IllegalArgumentException("Not exists plan"));
        PlanDto plan = planMapper.toDto(maybePlan);
        plan.setPlanStateList(planStateMapper.toDtoList(maybePlan.getPlanStateList()));
        return plan;
    }
}
