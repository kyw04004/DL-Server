package com.nerdnull.donlate.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.ConcurrentHashMap;

@Data
@AllArgsConstructor
public class CalculateParseDto {
    private Long planId;
    private ConcurrentHashMap<Long, Integer> userState;
}
