package org.bejb4.finalproject.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(String msg, HttpStatus status, Object resObj){
        Map<String, Object> mapResult = new HashMap<String,Object>();
        mapResult.put("message", msg);
        mapResult.put("code", status);
        mapResult.put("data", resObj);

        return new ResponseEntity<>(mapResult, status);
    }
}
