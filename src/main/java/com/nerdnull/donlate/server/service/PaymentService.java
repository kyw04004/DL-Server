package com.nerdnull.donlate.server.service;

import com.nerdnull.donlate.server.domain.PaymentEntity;
import com.nerdnull.donlate.server.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) { this.paymentRepository = paymentRepository; }

    public void delete(Long userId) {
        this.paymentRepository.deleteAllByUserId(userId);
    }
}
