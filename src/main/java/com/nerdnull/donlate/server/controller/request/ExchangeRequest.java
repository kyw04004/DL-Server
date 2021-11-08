package com.nerdnull.donlate.server.controller.request;

import lombok.Data;

import java.lang.reflect.Field;

@Data
public class ExchangeRequest {

    private String account;
    private Long amount;
    private String nickName;

    public void isNotNull() throws IllegalAccessException, IllegalArgumentException {
        for (Field f : getClass().getDeclaredFields()){
            if(f.get(this) == null)
                throw new IllegalAccessException(f.getName() + " could not be null");
        }
    }
}
