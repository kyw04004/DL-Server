package com.nerdnull.donlate.server.service;

import com.nerdnull.donlate.server.controller.request.CreatePlanRequest;
import com.nerdnull.donlate.server.controller.request.UpdatePlanRequest;
import com.nerdnull.donlate.server.domain.PlanEntity;
import com.nerdnull.donlate.server.dto.PlanDto;
import com.nerdnull.donlate.server.mapper.PlanMapper;
import com.nerdnull.donlate.server.repository.PlanRepository;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j// log
@Service
public class PlanService {

    private final PlanRepository planRepository;
    private final PlanMapper planMapper = Mappers.getMapper(PlanMapper.class);

    @Autowired
    public PlanService(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    /**
     *
     *  약속생성 기능, 프론트에서 약속관련 정보를 받아와 Entity 에 저장하고 다시 Dto 로 변환하여 반환
     *
     */
    public PlanDto setPlan(CreatePlanRequest planRequest) {
        try {
            PlanDto planDto = new PlanDto(null, planRequest.getAdmin(), planRequest.getDeposit(),
                    planRequest.getLatePercent(), planRequest.getAbsentPercent(), planRequest.getTitle(),
                    planRequest.getLocation(), planRequest.getDetailLocation(), planRequest.getDate(),
                    planRequest.getDone(), null);
            //PlanEntity e = planMapper.toEntity(planDto);
            PlanEntity saved = planRepository.save(planMapper.toEntity(planDto));
            return planMapper.toDto(saved);
        }
        catch (Exception e){
            throw e;
        }
    }

    /**
     *
     * 약속수정 기능, 프론트에서 수정할 약속에대한 정보를 가져와 해당 약속이 존재한다면 Entity 수정 후 Dto 로 변환하여 반환
     *
     */
    public PlanDto updatePlan(UpdatePlanRequest updatePlanRequest){
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

            return planMapper.toDto(saved);
        }
        catch (Exception e){
            throw e;
        }
    }

    /**
     *
     * 약속삭제 기능
     *
     */
    public void deletePlan(Long planId) {

            Optional<PlanEntity> target = planRepository.findById(planId);

            if (target.isEmpty()) {
                throw new IllegalArgumentException("Plan is not exist, can't delete");
            }

            planRepository.deleteById(planId);
    }

}
