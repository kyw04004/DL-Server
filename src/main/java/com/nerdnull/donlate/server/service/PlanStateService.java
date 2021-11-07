package com.nerdnull.donlate.server.service;

import com.nerdnull.donlate.server.domain.PlanEntity;
import com.nerdnull.donlate.server.domain.PlanStateEntity;
import com.nerdnull.donlate.server.dto.PlanStateDto;
import com.nerdnull.donlate.server.mapper.PlanStateMapper;
import com.nerdnull.donlate.server.repository.PlanRepository;
import com.nerdnull.donlate.server.repository.PlanStateRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PlanStateService {

    private final PlanRepository planRepository;
    private final PlanStateRepository planStateRepository;
    private final PlanStateMapper planStateMapper = Mappers.getMapper(PlanStateMapper.class);

    @Autowired
    public PlanStateService(PlanStateRepository planStateRepository, PlanRepository planRepository) {
        this.planStateRepository = planStateRepository;
        this.planRepository = planRepository;
    }

    /**
     *
     * 약속현황에 추가
     *
     */
    @SneakyThrows
    @Transactional
    public PlanStateDto setPlanState(PlanStateDto planStateDto){
        PlanStateEntity e = planStateRepository.save(planStateMapper.toEntity(planStateDto));
        return planStateMapper.toDto(e);
    }
}
