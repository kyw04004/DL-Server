package com.nerdnull.donlate.server.repository;

import com.nerdnull.donlate.server.domain.ExchangeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface ExchangeRepository extends JpaRepository<ExchangeEntity, Long> {
    List<ExchangeEntity> findAllByRequestedAtBefore(Date end);

    @Transactional
    void deleteAllByRequestedAtBefore(Date end);
}
