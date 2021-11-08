package com.nerdnull.donlate.server.service;

import com.nerdnull.donlate.server.dto.PaymentDto;
import com.nerdnull.donlate.server.mapper.PaymentMapper;
import com.nerdnull.donlate.server.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper = Mappers.getMapper(PaymentMapper.class);

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) { this.paymentRepository = paymentRepository; }

    public void add(PaymentDto paymentDto) {
        this.paymentRepository.save(this.paymentMapper.toEntity(paymentDto));
    }
    public void delete(Long userId) {
        this.paymentRepository.deleteAllByUserId(userId);
    }
}
