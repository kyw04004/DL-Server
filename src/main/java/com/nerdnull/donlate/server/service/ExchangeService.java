package com.nerdnull.donlate.server.service;

import com.nerdnull.donlate.server.dto.ExchangeDto;
import com.nerdnull.donlate.server.mapper.ExchangeMapper;
import com.nerdnull.donlate.server.repository.ExchangeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExchangeService {
    private final ExchangeRepository exchangeRepository;
    private final ExchangeMapper exchangeMapper;


    public void save(ExchangeDto exchange) {
        exchange.setRequestedAt(new Date());
        this.exchangeRepository.save(exchangeMapper.toEntity(exchange));
    }
}
