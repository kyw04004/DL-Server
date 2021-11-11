package com.nerdnull.donlate.server.scheduler;

import com.nerdnull.donlate.server.dto.PlanDto;
import com.nerdnull.donlate.server.dto.PlanStateDto;
import com.nerdnull.donlate.server.mapper.PlanMapper;
import com.nerdnull.donlate.server.mapper.PlanStateMapper;
import com.nerdnull.donlate.server.repository.PlanRepository;
import com.nerdnull.donlate.server.repository.PlanStateRepository;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;

@Component
@Slf4j
public class PlanScheduler {

    private final PlanStateRepository planStateRepository;
    private final PlanRepository planRepository;
    private final PlanStateMapper planStateMapper = Mappers.getMapper(PlanStateMapper.class);
    private final PlanMapper planMapper = Mappers.getMapper(PlanMapper.class);

    @Autowired
    public PlanScheduler(PlanStateRepository planStateRepository, PlanRepository planRepository) {
        this.planStateRepository = planStateRepository;
        this.planRepository = planRepository;
    }

    @Transactional
    @Scheduled(cron="0 0 0 * * *")
    public void run() {
        try {
            log.info("Plan scheduling job start--{}", LocalTime.now());
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.YEAR, -1);
            List<PlanStateDto> planStateList = planStateMapper.toDtoList(planStateRepository.findAll());
            List<PlanDto> planList = planMapper.toDtoList(planRepository.findAll());
            for (PlanStateDto p : planStateList) {
                int compare = cal.getTime().compareTo(p.getPlan().getDate());
                if (compare > 0) {
                    planStateRepository.deleteById(p.getPlanStateId());
                }
            }
            for (PlanDto p : planList) {
                int compare = cal.getTime().compareTo(p.getDate());
                if (compare > 0) {
                    planRepository.deleteById(p.getPlanId());
                }
            }
            log.info("Exchange scheduling job end--{}", LocalTime.now());
        } catch(Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
