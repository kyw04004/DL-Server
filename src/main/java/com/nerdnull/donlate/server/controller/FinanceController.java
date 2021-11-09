package com.nerdnull.donlate.server.controller;

import com.nerdnull.donlate.server.controller.request.ExchangeRequest;
import com.nerdnull.donlate.server.controller.response.GetPaymentListResponse;
import com.nerdnull.donlate.server.controller.response.Response;
import com.nerdnull.donlate.server.dto.ExchangeDto;
import com.nerdnull.donlate.server.dto.PaymentDto;
import com.nerdnull.donlate.server.service.ExchangeService;
import com.nerdnull.donlate.server.service.PaymentService;
import com.nerdnull.donlate.server.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequestMapping("/api/v1/finance")
@RestController
@Slf4j
public class FinanceController {
    private final ExchangeService exchangeService;
    private final PaymentService paymentService;
    private final UserService userService;

    @Autowired
    public FinanceController(ExchangeService exchangeService, PaymentService paymentService, UserService userService) {
        this.exchangeService = exchangeService;
        this.paymentService = paymentService;
        this.userService = userService;
    }

    @PostMapping("/exchange")
    public Response<String> exchange(@RequestBody ExchangeRequest request){
        try {
            request.isNotNull();

            ExchangeDto exchangeDto = new ExchangeDto(null, request.getBank(), request.getAccount(), request.getAmount(), request.getName(), null);
            this.exchangeService.save(exchangeDto);

            PaymentDto paymentDto = new PaymentDto(null, request.getAmount(), new Date(), request.getUserId(), null);
            this.paymentService.add(paymentDto);

            this.userService.exchange(request.getUserId(), request.getAmount());

            return Response.ok("Exchange request complete");
        }
        catch (IllegalAccessException | IllegalArgumentException e){
            log.error(e.getMessage(), e);
            return Response.error(Response.BAD_REQUEST, e.getMessage());
        }
        catch (Exception e){
            log.error(e.getMessage(), e);
            return Response.error(Response.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/payment/{userId}/list")
    public Response <GetPaymentListResponse> getPaymentList(@PathVariable Long userId){
        try {
            if(userId == null) {
                throw new IllegalArgumentException("Bad Request /api/v1/user/<Long> userID");
            }

            GetPaymentListResponse response = new GetPaymentListResponse(new ArrayList<>());
            List<PaymentDto> paymentList = this.paymentService.findByUserId(userId);
            for(PaymentDto payment : paymentList){
                response.add(payment.getUserId(), payment.getPaymentId(), payment.getAmount(), payment.getDate());
            }

            return Response.ok(response);
        }
        catch (IllegalArgumentException e){
            log.error(e.getMessage(), e);
            return Response.error(Response.BAD_REQUEST, e.getMessage());
        }
        catch (Exception e){
            log.error(e.getMessage(), e);
            return Response.error(Response.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping("/allocate")
    public Response<String> allocate(@RequestBody Long planId){

        return Response.ok("allocate complete");
    }
}
