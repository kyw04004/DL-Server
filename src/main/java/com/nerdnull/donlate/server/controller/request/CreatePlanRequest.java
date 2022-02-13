package com.nerdnull.donlate.server.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePlanRequest {
    private Long admin;
    private Long deposit;
    private Integer latePercent;
    private Integer absentPercent;
    private String title;
    private String location;
    private String detailLocation;
    private Date date;
    private Boolean done;

    public void isNotNull() throws IllegalAccessException, IllegalArgumentException {
        for (Field f : getClass().getDeclaredFields()){
            if(f.get(this) == null)
                throw new IllegalAccessException(f.getName() + " could not be null");
        }
    }

    public void checkDeposit() throws IllegalArgumentException{
        if(getDeposit() < 1000){
            throw new IllegalArgumentException("deposit min value is 1000");
        }
        else if(getDeposit()%100!=0){
            throw new IllegalArgumentException("deposit%100 != 0");
        }
    }

}
