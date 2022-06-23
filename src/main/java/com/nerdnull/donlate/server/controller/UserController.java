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
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/users")
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
    @GetMapping("/{userId}")
    public Response <UserDetailResponse> getUser(@PathVariable Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("Bad Request /api/v1/user/<Long> userID");
        }
        UserDto user = this.userService.getUser(userId);
        UserDetailResponse response = UserDetailResponse.builder()
                .userId(user.getUserId())
                .nickName(user.getNickName())
                .email(user.getEmail())
                .point(user.getPoint())
                .planStateList(user.getPlanStateList())
                .paymentList(user.getPaymentList())
                .build();

        return Response.ok(response);
    }

    /**
     *
     * User 정보 등록, 해당 User ID 반환
     *
     * @param request input(email, nickname)
     * @return userID
     */
    @PostMapping("/login")
    public Response<Long> login(@RequestBody LoginRequest request) throws IllegalAccessException {
        request.isNotNull();
        UserDto user = UserDto.builder()
                .nickName(request.getNickName())
                .email(request.getEmail())
                .build();

        return Response.ok(this.userService.login(user));
    }

    /**
     *
     * 회원 탈퇴, 유저와 관련된 약속 현황, payment list 삭제
     *
     * @param userId input(user id)
     * @return String message
     */
    @DeleteMapping("/{userId}")
    public Response<String> delete(@PathVariable Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("Bad Request /api/v1/user/<Long> userID");
        }
        this.paymentService.delete(userId);
        this.planStateService.deleteByUserId(userId);
        this.userService.delete(userId);
        return Response.ok("Delete user");
    }
}
