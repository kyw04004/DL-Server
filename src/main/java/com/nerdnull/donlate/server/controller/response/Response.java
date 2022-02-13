package com.nerdnull.donlate.server.controller.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public final class Response<T> {
    public static final Integer SUCCESS = 200;
    public static final Integer BAD_REQUEST = 400;
    public static final Integer INTERNAL_SERVER_ERROR = 500;
    private Integer status;
    private T data;
    private String message;

    public static <T> Response<T> ok(T data) {
        return new Response<>(SUCCESS, data, "Success");
    }
    public static <T> Response<T> error(Integer status, String message)  {
        return new Response<>(status, null, message);
    }
}
