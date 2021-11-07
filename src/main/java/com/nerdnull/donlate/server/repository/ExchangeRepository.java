package com.nerdnull.donlate.server.repository;

import com.nerdnull.donlate.server.domain.ExchangeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRepository extends JpaRepository<ExchangeEntity, Long> {
}
