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
            PlanDto planDto = PlanDto.builder()
                    .admin(planRequest.getAdmin())
                    .deposit(planRequest.getDeposit())
                    .latePercent(planRequest.getLatePercent())
                    .absentPercent(planRequest.getAbsentPercent())
                    .title(planRequest.getTitle())
                    .location(planRequest.getLocation())
                    .detailLocation(planRequest.getDetailLocation())
                    .date(planRequest.getDate())
                    .done(planRequest.getDone())
                    .build();
            PlanEntity saved = planRepository.save(planMapper.toEntity(planDto));
            return planMapper.toDto(saved);
        }
        catch (Exception e){
            throw e;
        }
    }

    public void updatePlan(UpdatePlanRequest updatePlanRequest){
        try {
            PlanDto planDto = PlanDto.builder()
                    .planId(updatePlanRequest.getPlanId())
                    .admin(updatePlanRequest.getAdmin())
                    .deposit(updatePlanRequest.getDeposit())
                    .latePercent(updatePlanRequest.getLatePercent())
                    .absentPercent(updatePlanRequest.getAbsentPercent())
                    .title(updatePlanRequest.getTitle())
                    .location(updatePlanRequest.getLocation())
                    .detailLocation(updatePlanRequest.getDetailLocation())
                    .date(updatePlanRequest.getDate())
                    .done(updatePlanRequest.getDone())
                    .build();
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
