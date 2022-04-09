package com.nerdnull.donlate.server.service;

import com.nerdnull.donlate.server.domain.PaymentEntity;
import com.nerdnull.donlate.server.dto.AllocateDto;
import com.nerdnull.donlate.server.dto.LateState;
import com.nerdnull.donlate.server.dto.PaymentDto;
import com.nerdnull.donlate.server.dto.PlanStateDto;
import com.nerdnull.donlate.server.mapper.PaymentMapper;
import com.nerdnull.donlate.server.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper = Mappers.getMapper(PaymentMapper.class);
    private final UserService userService;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, UserService userService) {
        this.paymentRepository = paymentRepository;
        this.userService = userService;
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

    public void basicAllocate(AllocateDto allocateDto) throws Exception {
        for (PlanStateDto p : allocateDto.getPlanStateList()) {
            LateState lateState = p.getLateState();
            Long userId = p.getUserId();
            if (lateState == LateState.NORMAL) this.userService.updatePoint(userId, allocateDto.getDeposit() + allocateDto.getToNormal());
        }
    }

    public void toOneAllocate(AllocateDto allocateDto) throws Exception {
        Random rand = new Random();
        int target = rand.nextInt(allocateDto.getNormalCnt());
        List<Long> userList = new ArrayList<>();
        List<PlanStateDto> planStateList = allocateDto.getPlanStateList();
        for (PlanStateDto p : planStateList) if(p.getLateState() == LateState.NORMAL)userList.add(p.getUserId());
        Collections.shuffle(userList);
        for (int i = 0; i < userList.size(); ++i) {
            if (i == 0) this.userService.updatePoint(userList.get(i), allocateDto.getDeposit() + allocateDto.getToNormal());
            else this.userService.updatePoint(userList.get(i), allocateDto.getDeposit());
        }
    }

    public void toHalfAllocate(AllocateDto allocateDto) throws Exception{
        int len = allocateDto.getNormalCnt() + 1;
        List<Long> userList = new ArrayList<>();
        List<PlanStateDto> planStateList = allocateDto.getPlanStateList();
        for (PlanStateDto p : planStateList) if(p.getLateState() == LateState.NORMAL)userList.add(p.getUserId());
        Collections.shuffle(userList);
        len /= 2;
        int cnt = 0;
        for (Long aLong : userList) {
            if (cnt++ < len) this.userService.updatePoint(aLong, allocateDto.getDeposit() + allocateDto.getToNormal() / len);
            else this.userService.updatePoint(aLong, allocateDto.getDeposit());
        }
    }
}
