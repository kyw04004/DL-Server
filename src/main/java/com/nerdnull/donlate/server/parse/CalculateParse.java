package com.nerdnull.donlate.server.parse;

import com.nerdnull.donlate.server.dto.CalculateParseDto;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
            Long lateState = (Long) jsonObj2.get("lateState");
            cal.getUserState().put(userId, lateState.intValue());
        }
        return cal;
    }
}
