package com.nerdnull.donlate.server.repository;

import com.nerdnull.donlate.server.domain.PlanStateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PlanStateRepository extends JpaRepository<PlanStateEntity,Long> {
    PlanStateEntity findByUserIdAndPlanId(Long userId, Long planId);

    @Transactional
    void deleteAllByUserId(Long userId);

    @Transactional
    void deleteAllByPlanId(Long planId);
}
