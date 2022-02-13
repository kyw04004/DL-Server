package com.nerdnull.donlate.server.service;

import com.nerdnull.donlate.server.dto.ExchangeDto;
import com.nerdnull.donlate.server.mapper.ExchangeMapper;
import com.nerdnull.donlate.server.repository.ExchangeRepository;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class ExchangeService {
    private final ExchangeRepository exchangeRepository;
    private final ExchangeMapper exchangeMapper = Mappers.getMapper(ExchangeMapper.class);

    @Autowired
    public ExchangeService(ExchangeRepository exchangeRepository) {
        this.exchangeRepository = exchangeRepository;
    }

    public void save(ExchangeDto exchange) {
        exchange.setRequestedAt(new Date());
        this.exchangeRepository.save(exchangeMapper.toEntity(exchange));
    }
}
