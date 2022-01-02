package com.nerdnull.donlate.server.controller.request;

import lombok.Data;

import java.lang.reflect.Field;

@Data
public class ExchangeRequest {

    private Long userId;
    private String bank;
    private String account;
    private Long point;
    private String name;

    public void isNotNull() throws IllegalAccessException, IllegalArgumentException {
        for (Field f : getClass().getDeclaredFields()){
            if(f.get(this) == null)
                throw new IllegalAccessException(f.getName() + " could not be null");
        }
    }

    public void checkPoint() throws IllegalArgumentException {
        if(this.point <= 0) throw new IllegalArgumentException("The point should be positive.");
    }
}
