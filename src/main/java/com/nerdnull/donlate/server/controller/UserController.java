package com.nerdnull.donlate.server.controller;

import com.nerdnull.donlate.server.controller.request.LoginRequest;
import com.nerdnull.donlate.server.controller.response.UserDetailResponse;
import com.nerdnull.donlate.server.controller.response.Response;
import com.nerdnull.donlate.server.dto.UserDto;
import com.nerdnull.donlate.server.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/user")
@RestController
@Slf4j
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/details/{userId}")
    public Response <UserDetailResponse> getUser(@PathVariable Long userId){
        try {
            if(userId == null) {
                String message = "Bad Request /api/v1/user/<Long>";
                log.error(message);
                Response.error(Response.BAD_REQUEST, message);
            }

            UserDto user = this.userService.getUser(userId);
            UserDetailResponse response = new UserDetailResponse(user.getUserId(), user.getNickName(), user.getEmail(),
                    user.getPoint(), user.getPlanStateList(), user.getPaymentList());

            return Response.ok(response);
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
            return Response.ok(userService.login(user));

        } catch (IllegalAccessException e) {
            log.error(e.getMessage(), e);
            return Response.error(Response.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Response.error(Response.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
