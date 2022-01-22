package com.nerdnull.donlate.server.service;

import com.nerdnull.donlate.server.dto.UserDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock UserService userService;

    @DisplayName("회원정보 조회")
    @Test
    void getUser() {
        //given
        UserDto userDto = new UserDto(1L,null,null,null,null,null);
        given(userService.getUser(1L)).willReturn(userDto);

        //when
        UserDto user2 = userService.getUser(1L);

        //then
        Assertions.assertThat(user2).isEqualTo(userDto);
        then(userService).should(times(1)).getUser(1L);
    }

    @DisplayName("회원 로그")
    @Test
    void login() {
        //given
        UserDto userDto = new UserDto(1L,null,null,null,null,null);
        given(userService.login(userDto)).willReturn(1L);

        //when
        Long id = userService.login(userDto);

        //then
        Assertions.assertThat(id).isEqualTo(1L);
        then(userService).should(times(1)).login(userDto);
    }

    @DisplayName("회원 포인트 수")
    @Test
    void updatePoint() throws Exception{
        //given
        willDoNothing().given(userService).updatePoint(1L, 1L);

        //when
        userService.updatePoint(1L,1L);

        //then
        then(userService).should(times(1)).updatePoint(1L,1L);
    }

    @DisplayName("회원정보 삭제")
    @Test
    void delete() {
        //given
        willDoNothing().given(userService).delete(1L);

        //when
        userService.delete(1L);

        //then
        then(userService).should(times(1)).delete(1L);
    }
}