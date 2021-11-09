package com.nerdnull.donlate.server.service;

import com.nerdnull.donlate.server.domain.PaymentEntity;
import com.nerdnull.donlate.server.domain.PlanEntity;
import com.nerdnull.donlate.server.domain.PlanStateEntity;
import com.nerdnull.donlate.server.dto.PaymentDto;
import com.nerdnull.donlate.server.mapper.PaymentMapper;
import com.nerdnull.donlate.server.repository.PaymentRepository;
import com.nerdnull.donlate.server.repository.PlanRepository;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PlanRepository planRepository;
    private final PaymentMapper paymentMapper = Mappers.getMapper(PaymentMapper.class);

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, PlanRepository planRepository) {
        this.paymentRepository = paymentRepository;
        this.planRepository = planRepository;
    }

    public List<PaymentDto> findByUserId(Long userId) {
        List<PaymentEntity> paymentList = this.paymentRepository.findAllByUserId(userId);
        return this.paymentMapper.toDtoList(paymentList);

    }

    public void add(PaymentDto paymentDto) {
        this.paymentRepository.save(this.paymentMapper.toEntity(paymentDto));
    }
    public void delete(Long userId) {
        this.paymentRepository.deleteAllByUserId(userId);
    }
    public void allocatePoint(Long planId){
        PlanEntity plan = planRepository.getById(planId);
        Integer latePercent = plan.getLatePercent();
        Integer absentPercent = plan.getAbsentPercent();
        Long deposit = plan.getDeposit();
        List<PlanStateEntity> planStateList = plan.getPlanStateList();
        int lateCnt = 0;
        int absentCnt = 0;
        int normalCnt = 0;

        for (PlanStateEntity p : planStateList) {
            Integer lateState = p.getLateState();
            if (lateState == 1)lateCnt++;
            else if (lateState == 2)absentCnt++;
            else normalCnt++;
        }

        Long toLatePerson = deposit * latePercent / 100;
        Long toAbsentPerson = deposit * absentCnt / 100;
      //  Long toNormalPerson = deposit + ();



    }

}
