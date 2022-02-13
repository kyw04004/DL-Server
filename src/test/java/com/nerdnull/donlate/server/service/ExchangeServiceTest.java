package com.nerdnull.donlate.server.service;

import com.nerdnull.donlate.server.dto.ExchangeDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class ExchangeServiceTest {

    @Mock
    ExchangeService exchangeService;

    @DisplayName("환전 요청")
    @Test
    void save() {
        // given
        ExchangeDto exchangeDto = new ExchangeDto(12L,"BANK","1234-12-1234",1000L,"Test",null);
        willDoNothing().given(exchangeService).save(exchangeDto);

        // when
        exchangeService.save(exchangeDto);

        // then
        then(exchangeService).should(times(1)).save(exchangeDto);
    }

}
