package com.nerdnull.donlate.server.service;

import com.nerdnull.donlate.server.domain.PlanEntity;
import com.nerdnull.donlate.server.domain.UserEntity;
import com.nerdnull.donlate.server.dto.UserDto;
import com.nerdnull.donlate.server.mapper.PaymentMapper;
import com.nerdnull.donlate.server.mapper.PlanStateMapper;
import com.nerdnull.donlate.server.mapper.UserMapper;
import com.nerdnull.donlate.server.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    private final PlanStateMapper planStateMapper = Mappers.getMapper(PlanStateMapper.class);
    private final PaymentMapper paymentMapper = Mappers.getMapper(PaymentMapper.class);

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserDto getUser(Long userId) {
        try {
            UserEntity maybeUser = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Not exists user"));
            UserDto user = userMapper.toDto(maybeUser);
            user.setPlanStateList(planStateMapper.toDtoList(maybeUser.getPlanStateList()));
            user.setPaymentList(paymentMapper.toDtoList(maybeUser.getPaymentList()));
            return user;
        }
        catch (Exception e){
            throw e;
        }
    }

    public Long login(UserDto user){
        UserEntity target = this.userRepository.findByEmail(user.getEmail());
        if(target == null){
            user.setPoint(0L);
        }
        else {
            user.setUserId(target.getUserId());
            user.setPoint(target.getPoint());
        }

        UserEntity saved = this.userRepository.save(userMapper.toEntity(user));
        return saved.getUserId();
    }
}
