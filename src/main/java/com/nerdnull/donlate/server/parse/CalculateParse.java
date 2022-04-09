package com.nerdnull.donlate.server.parse;

import com.nerdnull.donlate.server.dto.CalculateParseDto;
import com.nerdnull.donlate.server.dto.LateState;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class CalculateParse {
    public static CalculateParseDto parse(String body) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObj = (JSONObject)jsonParser.parse(body);
        Long planId = (Long) jsonObj.get("planId");
        JSONArray jsonArr = (JSONArray) jsonObj.get("userState");
        CalculateParseDto cal = new CalculateParseDto(planId,new ConcurrentHashMap<>());
        for (Object o : jsonArr) {
            JSONObject jsonObj2 = (JSONObject) o;
            Long userId = (Long) jsonObj2.get("userId");
            String lateState = (String) jsonObj2.get("lateState");
            if(Objects.equals(lateState, "NORMAL")) cal.getUserState().put(userId, LateState.NORMAL);
            if(Objects.equals(lateState, "LATE")) cal.getUserState().put(userId, LateState.LATE);
            if(Objects.equals(lateState, "ABSENT")) cal.getUserState().put(userId, LateState.ABSENT);
        }
        return cal;
    }
}
