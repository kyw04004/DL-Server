package com.nerdnull.donlate.server.controller.request;

import lombok.Data;

import java.lang.reflect.Field;

@Data
public class AllocateRequest {
    private Long planId;
    private Integer option; // 0 : n/1, 1 : to only one, 2 : to half

    public void isNotNull() throws IllegalAccessException, IllegalArgumentException {
        for (Field f : getClass().getDeclaredFields()){
            if(f.get(this) == null)
                throw new IllegalAccessException(f.getName() + " could not be null");
        }
    }
}
