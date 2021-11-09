package com.nerdnull.donlate.server.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class GetPaymentListResponse {

    List<GetPaymentResponse> paymentList;

    public void add(Long userId, Long paymentId, Long amount, Date date) {
        this.paymentList.add(new GetPaymentResponse(userId, paymentId, amount, date));
    }

    @Data
    @AllArgsConstructor
    private static class GetPaymentResponse {
        private Long userId;
        private Long paymentId;
        private Long amount;
        private Date date;
    }
}
