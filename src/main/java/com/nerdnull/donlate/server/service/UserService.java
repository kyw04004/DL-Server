package com.nerdnull.donlate.server.service;

import com.nerdnull.donlate.server.domain.UserEntity;
import com.nerdnull.donlate.server.dto.UserDto;
import com.nerdnull.donlate.server.mapper.PaymentMapper;
import com.nerdnull.donlate.server.mapper.PlanStateMapper;
import com.nerdnull.donlate.server.mapper.UserMapper;
import com.nerdnull.donlate.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PlanStateMapper planStateMapper;
    private final PaymentMapper paymentMapper;

    public UserDto getUser(Long userId) {
        UserEntity maybeUser = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Not exists user"));
        UserDto user = userMapper.toDto(maybeUser);
        user.setPlanStateList(planStateMapper.toDtoList(maybeUser.getPlanStateList()));
        user.setPaymentList(paymentMapper.toDtoList(maybeUser.getPaymentList()));
        return user;
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

    public void updatePoint(Long userId, Long diff) throws Exception {
        Optional<UserEntity> target = this.userRepository.findById(userId);
        if(target.isEmpty()){
            throw new IllegalArgumentException("Not exist user");
        }

        if(target.get().getPoint() + diff < 0) {
            throw new Exception("The user's point is insufficient.");
        }

        UserDto user = this.userMapper.toDto(target.get());
        user.setPoint(target.get().getPoint() + diff);

        this.userRepository.save(this.userMapper.toEntity(user));
    }

    public void delete(Long userId) {
        Optional<UserEntity> target = this.userRepository.findById(userId);
        target.ifPresent(userRepository::delete);
        if(target.isEmpty())
            throw new IllegalArgumentException("Not exist user");
    }
}
