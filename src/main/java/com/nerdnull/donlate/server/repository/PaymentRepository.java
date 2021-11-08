package com.nerdnull.donlate.server.repository;

import com.nerdnull.donlate.server.domain.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
    @Transactional
    void deleteAllByUserId(Long userId);
}
