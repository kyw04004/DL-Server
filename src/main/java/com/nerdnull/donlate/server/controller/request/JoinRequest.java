package com.nerdnull.donlate.server.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.lang.reflect.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JoinRequest {
    private Long planId;
    private Long userId;
    private Long point;
    private Long money;

    public void isNotNull() throws IllegalAccessException, IllegalArgumentException {
        for (Field f : getClass().getDeclaredFields()){
            if(f.get(this) == null)
                throw new IllegalAccessException(f.getName() + " could not be null");
        }
    }
}
