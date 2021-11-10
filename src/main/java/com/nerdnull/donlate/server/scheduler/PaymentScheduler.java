package com.nerdnull.donlate.server.scheduler;

import com.nerdnull.donlate.server.dto.PaymentDto;
import com.nerdnull.donlate.server.mapper.PaymentMapper;
import com.nerdnull.donlate.server.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;

@Component
@Slf4j
public class PaymentScheduler {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper = Mappers.getMapper(PaymentMapper.class);

    @Autowired
    public PaymentScheduler(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Transactional
    @Scheduled(cron="0 0 0 * * *")
    public void run() {
        try {
            log.info("Payment scheduling job start--{}", LocalTime.now());
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.YEAR, -1);
            log.info(cal.toString());
            List<PaymentDto> paymentList = paymentMapper.toDtoList(paymentRepository.findAll());
            for (PaymentDto p : paymentList) {
                int compare = cal.getTime().compareTo(p.getDate());
                log.info(p.getDate().toString());
                if (compare > 0) {
                    log.info(p.getPaymentId().toString());
                    paymentRepository.deleteById(p.getPaymentId());
                }
            }
        } catch(Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
