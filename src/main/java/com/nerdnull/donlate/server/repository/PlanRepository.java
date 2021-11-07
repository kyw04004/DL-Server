package com.nerdnull.donlate.server.repository;

import com.nerdnull.donlate.server.domain.PlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<PlanEntity,Long> {
}
