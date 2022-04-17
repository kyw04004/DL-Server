package com.nerdnull.donlate.server.controller;

import com.nerdnull.donlate.server.controller.request.LoginRequest;
import com.nerdnull.donlate.server.controller.response.UserDetailResponse;
import com.nerdnull.donlate.server.controller.response.Response;
import com.nerdnull.donlate.server.dto.UserDto;
import com.nerdnull.donlate.server.service.PaymentService;
import com.nerdnull.donlate.server.service.PlanStateService;
import com.nerdnull.donlate.server.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/user")
@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final PaymentService paymentService;
    private final PlanStateService planStateService;

    /**
     *
     * USER ID로 USER 정보 요청
     *
     * @param userId input(user ID)
     * @return User Info
     */
    @GetMapping("/details/{userId}")
    public Response <UserDetailResponse> getUser(@PathVariable Long userId){
        try {
            if(userId == null) {
                throw new IllegalArgumentException("Bad Request /api/v1/user/<Long> userID");
            }

            UserDto user = this.userService.getUser(userId);
            UserDetailResponse response = new UserDetailResponse(user.getUserId(), user.getNickName(), user.getEmail(),
                    user.getPoint(), user.getPlanStateList(), user.getPaymentList());

            return Response.ok(response);
        }
        catch (IllegalArgumentException e) {
            log.error(e.getMessage(), e);
            return Response.error(Response.BAD_REQUEST, e.getMessage());
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
            return Response.error(Response.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    /**
     *
     * User 정보 등록, 해당 User ID 반환
     *
     * @param request input(email, nickname)
     * @return userID
     */
    @PostMapping("/login")
    public Response<Long> login(@RequestBody LoginRequest request) {
        try {
            request.isNotNull();
            UserDto user = new UserDto(null, request.getNickName(),
                    request.getEmail(), null, null, null);
            return Response.ok(this.userService.login(user));

        }
        catch (IllegalAccessException | IllegalArgumentException e) {
            log.error(e.getMessage(), e);
            return Response.error(Response.BAD_REQUEST, e.getMessage());
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
            return Response.error(Response.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    /**
     *
     * 회원 탈퇴, 유저와 관련된 약속 현황, payment list 삭제
     *
     * @param userId input(user id)
     * @return String message
     */
    @DeleteMapping("/delete/{userId}")
    public Response<String> delete(@PathVariable Long userId) {
        try {
            if(userId == null) {
                throw new IllegalArgumentException("Bad Request /api/v1/user/<Long> userID");
            }

            this.paymentService.delete(userId);
            this.planStateService.deleteByUserId(userId);
            this.userService.delete(userId);
            return Response.ok("Delete user");
        }
        catch (IllegalArgumentException e) {
            log.error(e.getMessage(), e);
            return Response.error(Response.BAD_REQUEST, e.getMessage());
        }
        catch (Exception e){
            log.error(e.getMessage(), e);
            return Response.error(Response.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
