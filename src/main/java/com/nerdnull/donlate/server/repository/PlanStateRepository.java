package com.nerdnull.donlate.server.repository;

import com.nerdnull.donlate.server.domain.PlanStateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanStateRepository extends JpaRepository<PlanStateEntity,Long> {
}
