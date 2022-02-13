package com.nerdnull.donlate.server.service;

import com.nerdnull.donlate.server.dto.AllocateDto;
import com.nerdnull.donlate.server.dto.PaymentDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @Mock PaymentService paymentService;

    @DisplayName("유저 결제 내역 조회")
    @Test
    void findByUserId() {
        //given
        List<PaymentDto> paymentDtoList = new ArrayList<>();
        paymentDtoList.add(new PaymentDto(1L,null, null, null, null, null));
        given(paymentService.findByUserId(1L)).willReturn(paymentDtoList);

        //when
        List<PaymentDto> paymentDtoList2 = paymentService.findByUserId(1L);

        //then
        Assertions.assertThat(paymentDtoList).isEqualTo(paymentDtoList2);
        Assertions.assertThat(paymentDtoList2.size()).isEqualTo(1);
        then(paymentService).should(times(1)).findByUserId(1L);
    }

    @DisplayName("결제 내역 추가")
    @Test
    void add() {
        //given
        PaymentDto paymentDto = new PaymentDto(1L,null, null, null, null, null);
        willDoNothing().given(paymentService).add(paymentDto);

        //when
        paymentService.add(paymentDto);

        //then
        then(paymentService).should(times(1)).add(paymentDto);
    }

    @DisplayName("유저의 모든 결제 내역 삭제")
    @Test
    void delete() {
        //given
        willDoNothing().given(paymentService).delete(1L);

        //when
        paymentService.delete(1L);

        //then
        then(paymentService).should(times(1)).delete(1L);
    }

    @DisplayName("정상 참여자에게 n분의 1 분배")
    @Test
    void basicAllocate() throws Exception {
        //given
        AllocateDto allocateDto = new AllocateDto(null, null, null, 1);
        willDoNothing().given(paymentService).basicAllocate(allocateDto);

        //when
        paymentService.basicAllocate(allocateDto);

        //then
        then(paymentService).should(times(1)).basicAllocate(allocateDto);
    }

    @DisplayName("정상 참여자 중 1명에게 분배")
    @Test
    void toOneAllocate( ) throws Exception {
        //given
        AllocateDto allocateDto = new AllocateDto(null, null, null, 1);
        willDoNothing().given(paymentService).toOneAllocate(allocateDto);

        //when
        paymentService.toOneAllocate(allocateDto);

        //then
        then(paymentService).should(times(1)).toOneAllocate(allocateDto);
    }

    @DisplayName("정상 참여자 중 절반에게 분배")
    @Test
    void toHalfAllocate( ) throws Exception{
        //given
        AllocateDto allocateDto = new AllocateDto(null, null, null, 1);
        willDoNothing().given(paymentService).toHalfAllocate(allocateDto);

        //when
        paymentService.toHalfAllocate(allocateDto);

        //then
        then(paymentService).should(times(1)).toHalfAllocate(allocateDto);

    }
}