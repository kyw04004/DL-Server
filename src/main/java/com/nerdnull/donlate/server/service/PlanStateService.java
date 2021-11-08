package com.nerdnull.donlate.server.service;

import com.nerdnull.donlate.server.repository.PlanStateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PlanStateService {
    private final PlanStateRepository planStateRepository;

    @Autowired
    public PlanStateService(PlanStateRepository planStateRepository) {
        this.planStateRepository = planStateRepository;
    }

    public void delete(Long userId) {
        this.planStateRepository.deleteAllByUserId(userId);
    }
}
