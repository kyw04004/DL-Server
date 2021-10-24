package com.nerdnull.donlate.server.service;

import com.nerdnull.donlate.server.dto.PlanDto;
import com.nerdnull.donlate.server.mapper.PlanMapper;
import com.nerdnull.donlate.server.repository.PlanRepository;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j// log
@Service
public class PlanService {

    private final PlanRepository planRepository;
    private final PlanMapper planMapper = Mappers.getMapper(PlanMapper.class);

    @Autowired
    public PlanService(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    public void setDetails(PlanDto planDto)throws Exception{
       planRepository.save(planMapper.toEntity(planDto));
       
    }

}
