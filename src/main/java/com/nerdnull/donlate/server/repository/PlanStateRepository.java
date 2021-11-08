package com.nerdnull.donlate.server.repository;

import com.nerdnull.donlate.server.domain.PlanStateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface PlanStateRepository extends JpaRepository<PlanStateEntity,Long> {
    @Transactional
    void deleteAllByUserId(Long userId);
}
